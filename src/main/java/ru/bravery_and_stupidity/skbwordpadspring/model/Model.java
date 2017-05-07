package ru.bravery_and_stupidity.skbwordpadspring.model;

import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryEntity;

import java.util.*;

public interface Model {

  /**
   *  Метод для закгрузки словаря в модель
   *  @param dictionary словарь
   */
  void setDictionary(TreeSet<DictionaryEntity> dictionary);

  /**
   * Метод загрузки слов, введенных пользователем в модель
   * @param words Коллекция с введенным данными
   */
  void setInputWords(ArrayList<String> words);


  /**
   * Метод возвращающий слова для автозаполнения
   * @return Коллекция слов для автозаполнения
   */
  ArrayList<String> getOutputWords();
}
