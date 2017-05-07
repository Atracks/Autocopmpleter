package ru.bravery_and_stupidity.skbwordpadspring.dictionary;

final public class DictionaryEntity {
  private String word;
  private Integer countOfEntry;

  public DictionaryEntity(String word) {
    this.word = word;
    this.countOfEntry = 0;
  }

  public DictionaryEntity(String word, Integer countOfEntry) {
    this.word = word;
    this.countOfEntry = countOfEntry;
  }

  public String getWord() {
    return word;
  }

  public Integer getCountOfEntry() {
    return countOfEntry;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj.getClass() == DictionaryEntity.class) {
      if((this.getWord().compareToIgnoreCase(((DictionaryEntity)obj).getWord()) == 0)&&
          this.getCountOfEntry().compareTo(((DictionaryEntity)obj).getCountOfEntry()) == 0) {
        return true;
      }
      return false;
    }
    return super.equals(obj);
  }
}
