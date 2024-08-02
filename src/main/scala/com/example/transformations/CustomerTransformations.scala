package com.example.transformations

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object CustomerTransformations {
  def cleanAndTransform(df: DataFrame): DataFrame = {
    df.withColumn("name", initcap(col("name")))
      .withColumn("email", lower(col("email")))
      .withColumn("age_group", 
        when(col("age") < 18, "Under 18")
          .when(col("age").between(18, 30), "18-30")
          .when(col("age").between(31, 50), "31-50")
          .otherwise("Over 50"))
      .filter(col("age") > 0)
  }
}