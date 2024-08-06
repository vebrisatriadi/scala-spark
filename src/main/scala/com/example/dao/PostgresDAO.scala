package com.example.dao

import org.apache.spark.sql.{DataFrame, SparkSession}

class PostgresDAO(spark: SparkSession) {

  def readTable(jdbcUrl: String, user: String, password: String, tableName: String) = {
    spark.read
      .format("jdbc")
      .option("url", jdbcUrl)
      .option("dbtable", tableName)
      .option("user", user)
      .option("password", password)
      .option("driver", "org.postgresql.Driver")
      .load()
  }

  def writeTable(df: DataFrame, jdbcUrl: String, user: String, password: String, tableName: String): Unit = {
    df.write
      .format("jdbc")
      .option("url", jdbcUrl)
      .option("dbtable", tableName)
      .option("user", user)
      .option("password", password)
      .option("driver", "org.postgresql.Driver")
      .mode("overwrite")
      .save()
    }
}