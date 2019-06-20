# rabbit
Dead-letter-queue-example

 RabbitMQ integration made easy with Spring-boot AMQP.
 

    Build & Run Consumer & Producer-      
    
    
 a.) mvn clean package spring-boot:repackage     
 b.) java -jar -Dserver.port={port} /target/{jarFileName}     
 
  Note - Default port is 8080. Please change it in any one app.     
  
  Features - 
  1.) Setup Dead Letter Queue and exchange to monitor unprocessed or failed messages.     
  2.) Messages those were not processed in queue(sms) will end up in Queue name (sms-dead-letter.queue).     
  3.) Easy MQ connection factory , retry message logic, setup max attempts for a particular message.     

  
