package com.example

import com.example.config.AppConfig
import com.example.dao.PostgresDAO
import com.example.transformations.CustomerTransformations
import org.apache.spark.sql.SparkSession
import org.apache.logging.log4j.{LogManager, Logger}

object Main {
  private val logger: Logger = LogManager.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(AppConfig.Spark.appName)
      .master(AppConfig.Spark.master)
      .getOrCreate()

    try {
        logger.info("Starting data ingestion process")

        val postgresDAO = new PostgresDAO(spark)
        
        val sourceDF = postgresDAO.readTable(AppConfig.Ingestion.sourceTable)
        logger.info(s"Read ${sourceDF.count()} records from source table")

    //   val transformedDF = CustomerTransformations.cleanAndTransform(sourceDF)
        val transformedDF = sourceDF

        logger.info(s"Transformed data, resulting in ${transformedDF.count()} records")

        postgresDAO.writeTable(transformedDF, AppConfig.Ingestion.targetTable)
        logger.info(s"Written data to target table ${AppConfig.Ingestion.targetTable}")

        logger.info("Data ingestion process completed successfully")
    } catch {
      case e: Exception =>
        logger.error(s"Error during data ingestion: ${e.getMessage}", e)
    } finally {
        spark.stop()
    }
  }
}