package com.example

import org.apache.spark.sql.SparkSession

object SparkApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkScalaProject")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val data = Seq(("Alice", 25), ("Bob", 30), ("Charlie", 35))
    val df = data.toDF("Name", "Age")

    df.show()

    df.select("Name").show()
    df.filter($"Age" > 30).show()

    spark.stop()
  }
}