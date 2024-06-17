
  package com.tech.eks.student.kafka;
  
  import java.util.Map;
  
  import org.apache.kafka.clients.admin.NewTopic; import
  org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.kafka.config.TopicBuilder;
  
  @Configuration 
  public class KafkaConfiguration 
  { 
	  public KafkaConfiguration()
  { 
		  System.out.println("KafkaConfiguration is created ***** ");
		  }
  
  @Bean
  NewTopic createTopic() {
	  return TopicBuilder.name("student-events-topic") .partitions(3) .replicas(1)
                         .configs(Map.of("min.insync.replicas","1")) .build(); }
  
  }
 