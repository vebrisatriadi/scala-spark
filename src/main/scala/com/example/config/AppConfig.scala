package com.example.config

import com.typesafe.config.ConfigFactory

object AppConfig {
  private val config = ConfigFactory.load()

  object Postgres {
    val url = config.getString("postgres.url")
    val user = config.getString("postgres.user")
    val password = config.getString("postgres.password")
    val driver = config.getString("postgres.driver")
  }

  object Spark {
    val appName = config.getString("spark.app-name")
    val master = config.getString("spark.master")
  }

  object Ingestion {
    val sourceTable = config.getString("ingestion.source-table")
    val targetTable = config.getString("ingestion.target-table")
    val batchSize = config.getInt("ingestion.batch-size")
  }
}