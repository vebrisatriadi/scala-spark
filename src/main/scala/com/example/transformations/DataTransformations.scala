package com.example.transformations

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object DataTransformations{
  def cleanAndTransform(df: DataFrame): DataFrame = {
    df.withColumn("processed_timestamp", current_timestamp())
      .withColumn("processed_date", current_date())
  }
}