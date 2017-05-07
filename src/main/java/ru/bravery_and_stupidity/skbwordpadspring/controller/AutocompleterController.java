package ru.bravery_and_stupidity.skbwordpadspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bravery_and_stupidity.skbwordpadspring.service.AutocompleterService;

import java.util.List;

@Controller
@RequestMapping(value = "/autocompleter")
final public class AutocompleterController {
  @Autowired
  private AutocompleterService service;

  @RequestMapping(method = RequestMethod.GET)
  public String renderWebView() {
    return "WEB-INF/pages/index.html";
  }

  @RequestMapping(value = "/complete/", method = RequestMethod.GET)
  @ResponseBody
  public List<String> findAutocompleteWords(@RequestParam("word") String inputWord) {
    return service.findAutocompleteWords(inputWord);
  }
}
