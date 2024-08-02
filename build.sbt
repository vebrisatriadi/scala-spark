name := "ComplexSparkPostgresIngestion"
version := "0.1.0"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.0",
  "org.apache.spark" %% "spark-sql" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.3.1",
  "com.typesafe" % "config" % "1.4.1",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "org.apache.logging.log4j" % "log4j-core" % "2.14.1",
  "org.apache.logging.log4j" % "log4j-api" % "2.14.1"
)
