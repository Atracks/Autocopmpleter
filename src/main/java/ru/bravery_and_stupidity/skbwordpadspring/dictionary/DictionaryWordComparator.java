package ru.bravery_and_stupidity.skbwordpadspring.dictionary;


import java.util.Comparator;

final public class DictionaryWordComparator implements Comparator<DictionaryEntity> {
  @Override
  public int compare(DictionaryEntity o1, DictionaryEntity o2){
    int result = o1.getWord().compareToIgnoreCase(o2.getWord());
    return result;
  }
}
