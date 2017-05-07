package ru.bravery_and_stupidity.skbwordpadspring.service;

import java.util.List;

public interface AutocompleterService {
  List<String> findAutocompleteWords(String inputWord);
}
