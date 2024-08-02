package com.example

import org.apache.spark.sql.{SparkSession, SaveMode}

object PostgresIngestion {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("PostgresIngestion")
      .master("local[*]")
      .getOrCreate()

    val jdbcUrl = "jdbc:postgresql://localhost:5432/alodokter"
    val connectionProperties = new java.util.Properties()
    connectionProperties.put("user", "postgres")
    connectionProperties.put("password", "12345654321")
    connectionProperties.put("driver", "org.postgresql.Driver")

    val sourceDF = spark.read
      .jdbc(jdbcUrl, "actor", connectionProperties)

    val transformedDF = sourceDF.withColumnRenamed("first_name", "1st_name")

    transformedDF.write
      .mode(SaveMode.Overwrite)
      .jdbc(jdbcUrl, "pg_actor", connectionProperties)

    println("Ingestion succeded")

    spark.stop()
  }
}