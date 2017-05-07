package ru.bravery_and_stupidity.skbwordpadspring.parser;


import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public interface InputDataReader {

  /**
   *  Метод, для чтения входных данных из ресурса
   */
  void readData() throws Exception;

  /**
   * @return словарь для заданного языка
   */
  TreeSet<DictionaryEntity> getDictionary();

  /**
   * @return набор входных слов
   */
  ArrayList<String> getInputWords();

  /**
   * @return True, если были ошибки при парсинге
   *         файла входных данных
   */
  boolean hasErrors();

  /**
   * @return Список ошибок, возникших при
   * парсинге файла входных данных
   */
  List<String> getParsingErrors();
}
