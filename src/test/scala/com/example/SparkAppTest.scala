// package com.example

// import org.apache.spark.sql.SparkSession
// import org.scalatest.funsuite.AnyFunSuite

// class SparkAppTest extends AnyFunSuite {
  
//   test("SparkSession should be created") {
//     val spark = SparkSession.builder()
//       .appName("SparkScalaProjectTest")
//       .master("local[*]")
//       .getOrCreate()

//     assert(spark.sparkContext.appName == "SparkScalaProjectTest")
    
//     spark.stop()
//   }
// }