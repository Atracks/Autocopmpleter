package ru.bravery_and_stupidity.skbwordpadspring.model;

import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryCountOfEntryComparator;
import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryEntity;
import org.springframework.util.Assert;

import java.util.*;

final public class AutocompleterModel implements Model {
  private TreeSet<DictionaryEntity> dictionary;
  private ArrayList<String> inputWords;
  private ArrayList<DictionaryEntity> outputWords = new ArrayList<>();

  @Override
  public void setDictionary(TreeSet<DictionaryEntity> dictionary) {
    Assert.notNull(dictionary, "unexpected null value");
    this.dictionary = dictionary;
  }

  @Override
  public void setInputWords(ArrayList<String> inputWords) {
    Assert.notNull(inputWords, "unexpected null value");
    this.inputWords = inputWords;
  }

  @Override
  public ArrayList<String> getOutputWords() {
    findAutoCompleteWords();
    return convertToStringList();
  }

  public void findAutoCompleteWords() {
    for (String upperBoard: inputWords) {
      String lowerBoard = defineLowerBoard(upperBoard);
      SortedSet<DictionaryEntity> interval = getInterval(upperBoard,lowerBoard);
      outputWords.addAll(getSortedWordsByEntryCounts(interval));
    }
  }

  private String defineLowerBoard(String upperBoard) {
    char lastChar = upperBoard.charAt(upperBoard.length() - 1);
    lastChar++;
    String lowerBoard = upperBoard;
    lowerBoard = lowerBoard.substring(0,upperBoard.length() - 1);
    lowerBoard += lastChar;
    return lowerBoard;
  }

  private SortedSet<DictionaryEntity> getInterval(String upperBoard, String lowerBoard) {
    DictionaryEntity entityUpperBoard = new DictionaryEntity(upperBoard);
    SortedSet<DictionaryEntity> interval = dictionary.tailSet(entityUpperBoard);
    DictionaryEntity entityLowerBoard = new DictionaryEntity(lowerBoard);
    interval = interval.headSet(entityLowerBoard);
    return interval;
  }

  private SortedSet<DictionaryEntity> getSortedWordsByEntryCounts(SortedSet<DictionaryEntity> interval) {
    final int wordsCount = 10;
    SortedSet<DictionaryEntity> sortedWordsByEntryCounts = new TreeSet<>(new DictionaryCountOfEntryComparator());
    addFirstEntity(sortedWordsByEntryCounts,interval);
    for (DictionaryEntity entry : interval) {
      addGreaterEntity(entry,sortedWordsByEntryCounts,wordsCount);
      removeGreaterThenWordsCount(sortedWordsByEntryCounts,wordsCount);
    }
    return sortedWordsByEntryCounts;
  }

  private void addFirstEntity(SortedSet<DictionaryEntity> sortedWordsByEntryCounts, SortedSet<DictionaryEntity> interval) {
    if(!interval.isEmpty()){
      sortedWordsByEntryCounts.add(interval.first());
    }
  }

  private void addGreaterEntity(DictionaryEntity entry, SortedSet<DictionaryEntity> sortedWordsByEntryCounts, int wordsCount) {
    if((entry.getCountOfEntry() >= sortedWordsByEntryCounts.last().getCountOfEntry())||
        (sortedWordsByEntryCounts.size() < wordsCount)) {
      sortedWordsByEntryCounts.add(entry);
    }
  }

  private void removeGreaterThenWordsCount(SortedSet<DictionaryEntity> sortedWordsByEntryCounts, int wordsCount) {
    if(sortedWordsByEntryCounts.size() > wordsCount) {
      sortedWordsByEntryCounts.remove(sortedWordsByEntryCounts.last());
    }
  }

  private ArrayList<String> convertToStringList() {
    ArrayList<String> words = new ArrayList<>();
    for (DictionaryEntity entity: outputWords) {
      words.add(entity.getWord());
    }
    return words;
  }
}
