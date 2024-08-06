package com.example

import com.example.config.AppConfig
import com.example.dao.PostgresDAO
import com.example.transformations.DataTransformations
import org.apache.spark.sql.SparkSession
import org.apache.logging.log4j.{LogManager, Logger}

object Main {
  private val logger: Logger = LogManager.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    AppConfig.parse(args) match {
      case Some(config) => run(config)
      case None => 
        logger.error("Failed to parse arguments")
        System.exit(1)
    }
  }

  def run(config: AppConfig): Unit = {
    val spark = SparkSession.builder()
      .appName("ParameterizedSparkPostgresIngestion")
      .getOrCreate()

    try {
      logger.info("Starting data ingestion process")

      val postgresDAO = new PostgresDAO(spark)
      
      val sourceDF = postgresDAO.readTable(
        config.sourceJdbcUrl, config.sourceUser, config.sourcePassword, config.sourceTable
      )
      logger.info(s"Read ${sourceDF.count()} records from source table ${config.sourceTable}")

      val transformedDF = DataTransformations.cleanAndTransform(sourceDF)
      logger.info(s"Transformed data, resulting in ${transformedDF.count()} records")

      postgresDAO.writeTable(
        transformedDF, config.targetJdbcUrl, config.targetUser, config.targetPassword, config.targetTable
      )
      logger.info(s"Written data to target table ${config.targetTable}")

      logger.info("Data ingestion process completed successfully")
    } catch {
      case e: Exception =>
        logger.error(s"Error during data ingestion: ${e.getMessage}", e)
    } finally {
      spark.stop()
    }
  }
}