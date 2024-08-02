package com.example.dao

import com.example.config.AppConfig
import org.apache.spark.sql.{DataFrame, SparkSession}

class PostgresDAO(spark: SparkSession) {
  import spark.implicits._

  private val jdbcUrl = AppConfig.Postgres.url
  private val connectionProperties = new java.util.Properties()
  connectionProperties.put("user", AppConfig.Postgres.user)
  connectionProperties.put("password", AppConfig.Postgres.password)
  connectionProperties.put("driver", AppConfig.Postgres.driver)

  def readTable(tableName: String): DataFrame = {
    spark.read.jdbc(jdbcUrl, tableName, connectionProperties)
  }

  def writeTable(df: DataFrame, tableName: String): Unit = {
    df.write
      .mode("overwrite")
      .jdbc(jdbcUrl, tableName, connectionProperties)
  }
}