# kafka-simple-example

bin/zookeeper-server-start.sh config/zookeeper.properties

./bin/kafka-server-start.sh config/server.properties


./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning
