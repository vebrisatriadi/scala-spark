# Mini Spark Postgres Ingestion with Scala

How to run:

```bash
spark-submit \
  --class com.example.Main \
  --master local[*] \
  /Users/vebrisatriadi/Documents/Portfolio/scala-spark/target/scala-2.12/ComplexSparkPostgresIngestion-assembly-0.1.0.jar \
  --source-jdbc-url jdbc:postgresql://source-host:5432/source-db \
  --source-user source-username \
  --source-password source-password \
  --source-table source_schema.source_table \
  --target-jdbc-url jdbc:postgresql://target-host:5432/target-db \
  --target-user target-username \
  --target-password target-password \
  --target-table target_schema.target_table
```