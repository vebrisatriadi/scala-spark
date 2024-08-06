package com.example.config

case class AppConfig(
  sourceJdbcUrl: String = "",
  sourceUser: String = "",
  sourcePassword: String = "",
  sourceTable: String = "",
  targetJdbcUrl: String = "",
  targetUser: String = "",
  targetPassword: String = "",
  targetTable: String = ""
)

object AppConfig {
  def parse(args: Array[String]): Option[AppConfig] = {
    val parser = new scopt.OptionParser[AppConfig]("SparkPostgresIngestion") {
      opt[String]("source-jdbc-url").required().action((x, c) => c.copy(sourceJdbcUrl = x))
      opt[String]("source-user").required().action((x, c) => c.copy(sourceUser = x))
      opt[String]("source-password").required().action((x, c) => c.copy(sourcePassword = x))
      opt[String]("source-table").required().action((x, c) => c.copy(sourceTable = x))
      opt[String]("target-jdbc-url").required().action((x, c) => c.copy(targetJdbcUrl = x))
      opt[String]("target-user").required().action((x, c) => c.copy(targetUser = x))
      opt[String]("target-password").required().action((x, c) => c.copy(targetPassword = x))
      opt[String]("target-table").required().action((x, c) => c.copy(targetTable = x))
    }

    parser.parse(args, AppConfig())
  }
}