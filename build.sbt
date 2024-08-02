name := "SparkPostgresIngestion"
version := "0.1.0"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.0",
  "org.apache.spark" %% "spark-sql" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.3.1",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"