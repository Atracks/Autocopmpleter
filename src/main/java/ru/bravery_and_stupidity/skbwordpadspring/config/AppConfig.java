package ru.bravery_and_stupidity.skbwordpadspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.bravery_and_stupidity.skbwordpadspring.parser.FileParser;
import ru.bravery_and_stupidity.skbwordpadspring.parser.InputDataReader;
import ru.bravery_and_stupidity.skbwordpadspring.service.AutocompleterService;
import ru.bravery_and_stupidity.skbwordpadspring.service.AutocompleterServiceImpl;

import java.net.URL;

@Configuration
public class AppConfig {
  @Bean
  @Scope(value = "singleton")
  public InputDataReader fileInputReader() throws Exception {
    URL url = this.getClass().getClassLoader().getResource("dictionary.in");
    InputDataReader parser = new FileParser(url.getFile());
    parser.readData();
    return parser;
  }

  @Bean
  @Scope(value = "singleton")
  AutocompleterService service(){
    return new AutocompleterServiceImpl();
  }
}
