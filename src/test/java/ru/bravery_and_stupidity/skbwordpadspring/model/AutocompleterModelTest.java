package ru.bravery_and_stupidity.skbwordpadspring.model;

import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryEntity;
import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryWordComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.bravery_and_stupidity.skbwordpadspring.parser.FileParser;
import ru.bravery_and_stupidity.skbwordpadspring.system_tools.Logger;
import java.util.ArrayList;
import java.util.TreeSet;

public class AutocompleterModelTest {
  private ArrayList<DictionaryEntity> arrayEntity;

  @Before
  public void setUp() throws Exception {
    Logger.initLog();
    arrayEntity = new ArrayList<>();
  }

  @Test
  public void findAutoCompleteWordsSkbTest() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/dictionary.in");
    Model model = new AutocompleterModel();
    parser.readData();
    model.setDictionary(parser.getDictionary());
    model.setInputWords(parser.getInputWords());
    model.getOutputWords();
  }

  @Test
  public void findAutoCompleteWords() {
    Model model = new AutocompleterModel();
    TreeSet<DictionaryEntity> dictionary = new TreeSet<>(new DictionaryWordComparator());
    ArrayList<String> inputWords = new ArrayList<>();
    setDictionary(dictionary);
    setInputWords(inputWords);
    model.setDictionary(dictionary);
    model.setInputWords(inputWords);
    Assert.assertTrue(checkOutputWords(model));
  }

  private void setDictionary(TreeSet<DictionaryEntity> dictionary) {
    arrayEntity.add(0, new DictionaryEntity("aaabbbccc",153));
    arrayEntity.add(1, new DictionaryEntity("acabbc", 18));
    arrayEntity.add(2, new DictionaryEntity("cabbac", 6789));
    arrayEntity.add(3, new DictionaryEntity("zzzzzzzz", 754));
    arrayEntity.add(4, new DictionaryEntity("xzxzxzxzx", 6459));
    arrayEntity.add(5, new DictionaryEntity("azcbavccc", 1));
    arrayEntity.add(6, new DictionaryEntity("aaagggdcv", 56));
    arrayEntity.add(7, new DictionaryEntity("aaaf", 78));
    arrayEntity.add(8, new DictionaryEntity("aaagggdcz", 153));
    arrayEntity.add(9, new DictionaryEntity("x", 22));
    arrayEntity.add(10, new DictionaryEntity("bbbbbbbbbbb", 11));
    arrayEntity.add(11, new DictionaryEntity("bbbbbbbbbb", 2));
    arrayEntity.add(12, new DictionaryEntity("bbbbbbbbb", 3));
    arrayEntity.add(13, new DictionaryEntity("bbbbbbbb", 4));
    arrayEntity.add(14, new DictionaryEntity("bbbbbbb", 5));
    arrayEntity.add(15, new DictionaryEntity("bbbbbb", 6));
    arrayEntity.add(16, new DictionaryEntity("bbbbb", 7));
    arrayEntity.add(17, new DictionaryEntity("bbbb", 8));
    arrayEntity.add(18, new DictionaryEntity("bbb", 9));
    arrayEntity.add(19, new DictionaryEntity("bb", 10));
    arrayEntity.add(20, new DictionaryEntity("b", 1));
    arrayEntity.add(21, new DictionaryEntity("w", 55));
    arrayEntity.add(22, new DictionaryEntity("wc", 55));
    arrayEntity.add(23, new DictionaryEntity("wb", 55));
    arrayEntity.add(24, new DictionaryEntity("wa", 55));
    dictionary.addAll(arrayEntity);
  }

  private void setInputWords(ArrayList<String> inputWords) {
    inputWords.add("aaa");
    inputWords.add("ca");
    inputWords.add("x");
    inputWords.add("b");
    inputWords.add("bb");
    inputWords.add("bbbbbbbbbb");
    inputWords.add("ycv");
    inputWords.add("w");
  }

  private boolean checkOutputWords(Model model) {
    ArrayList<String> outputWords = model.getOutputWords();
    if(!outputWords.equals(getEtalonOutputWords())) {
      return false;
    }
    return true;
  }

  public ArrayList<String> getEtalonOutputWords() {
    ArrayList<String> etalonOutputWords = new ArrayList<>();
    int indexArray[] = new int[]{0, 8, 7, 6,                             //input word: aaa
                                 2,                                      //input word: ca
                                 4, 9,                                   //input word: x
                                 10, 19, 18, 17, 16, 15, 14, 13, 12, 11, //input word: b
                                 10, 19, 18, 17, 16, 15, 14, 13, 12, 11, //input word: bb
                                 10, 11,                                 //input word: bbbbbbbbbb
                                 21, 24, 23, 22};                        //input word: w
    for (int index: indexArray){
      etalonOutputWords.add(arrayEntity.get(index).getWord());
    }
    return  etalonOutputWords;
  }
}