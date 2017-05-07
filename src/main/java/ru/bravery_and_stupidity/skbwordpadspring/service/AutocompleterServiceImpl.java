package ru.bravery_and_stupidity.skbwordpadspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import ru.bravery_and_stupidity.skbwordpadspring.model.AutocompleterModel;
import ru.bravery_and_stupidity.skbwordpadspring.model.Model;
import ru.bravery_and_stupidity.skbwordpadspring.parser.InputDataReader;
import ru.bravery_and_stupidity.skbwordpadspring.system_tools.Toolkit;

import java.util.ArrayList;
import java.util.List;

public class AutocompleterServiceImpl implements AutocompleterService {
  @Autowired
  private InputDataReader fileInputReader;

  @Override
  public List<String> findAutocompleteWords(String inputWord) {
    List<String> words = new ArrayList<>();
    Toolkit.requireNotNull(inputWord);
    if(!inputWord.isEmpty()) {
      Model model = new AutocompleterModel();
      model.setDictionary(fileInputReader.getDictionary());
      setInputWord(model,inputWord);
      words = model.getOutputWords();
    }
    return words;
  }

  private void setInputWord(Model model, String inputWord) {
    ArrayList<String> inputWords = new ArrayList<>();
    inputWords.add(inputWord);
    model.setInputWords(inputWords);
  }
}
