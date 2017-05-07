package ru.bravery_and_stupidity.skbwordpadspring.parser;


import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryEntity;
import ru.bravery_and_stupidity.skbwordpadspring.dictionary.DictionaryWordComparator;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class FileParser implements InputDataReader {
  private TreeSet<DictionaryEntity> dictionary;
  private ArrayList<String> inputWords;
  private Scanner scanner;
  private int currentLineNumber = 1;
  private String filename;
  private List<String> parsingErrors;

  public FileParser(String filename) {
    this.dictionary = new TreeSet<>(new DictionaryWordComparator());
    this.inputWords = new ArrayList<>();
    this.filename = filename;
    parsingErrors = new ArrayList<>();
  }

  public List<String> getParsingErrors() {
    return parsingErrors;
  }

  public boolean hasErrors() {
    return !parsingErrors.isEmpty();
  }

  @Override
  public TreeSet<DictionaryEntity> getDictionary() {
    return dictionary;
  }

  @Override
  public ArrayList<String> getInputWords() {
    return inputWords;
  }

  @Override
  public void readData() {
    openFile();
    if(!hasErrors()) {readFromFile();}
  }

  private void openFile() {
    try {
      scanner = new Scanner(new File(filename));
    } catch (Exception ex) {
      parsingErrors.add(ParserSettings.MSG_FILE_NOT_FOUND);
    }
  }

  private void readFromFile() {
    clearCurrentData();
    readDictionary();
    readInputWords();
  }

  private void clearCurrentData() {
    currentLineNumber = 1;
    dictionary.clear();
    inputWords.clear();
    parsingErrors.clear();
  }

  private void readDictionary() {
    int dictionarySize = readIntegerValue();
    for (int i = 0; i < dictionarySize; i++) {
      if (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        parseDictionaryLine(line);
        ++currentLineNumber;
      } else {
        parsingErrors.add(ParserSettings.MSG_NEXT_LINE_EXPECTED + String.valueOf(currentLineNumber));
      }
    }
  }

  private void readInputWords() {
    int inputWordsSize = readIntegerValue();
    for (int i = 0; i < inputWordsSize; i++) {
      if (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        inputWords.add(getWord(line));
        ++currentLineNumber;
      } else {
        parsingErrors.add(ParserSettings.MSG_NEXT_LINE_EXPECTED + String.valueOf(currentLineNumber));
        return;
      }
    }
  }

  private int readIntegerValue() {
    int sizeInt = 0;
    if (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      sizeInt = parseIntegerValue(line);
      ++currentLineNumber;
    } else {
      parsingErrors.add(ParserSettings.MSG_INT_VALUE_EXPECTED + String.valueOf(currentLineNumber));
    }
    return sizeInt;
  }

  private int parseIntegerValue(String line) {
    if(!checkFormatLine(line, ParserSettings.INT_VALUE_PATTERN)) {return 0;}
    int value = getIntegerValue(line);
    return value;
  }

  private void parseDictionaryLine(String line) {
    if(!checkFormatLine(line, ParserSettings.DICTIONARY_ENTITY_PATTERN)) {return;}
    String word = getWord(line);
    Integer countOfEntry = getIntegerValue(line);
    dictionary.add(new DictionaryEntity(word, countOfEntry));
  }

  private boolean checkFormatLine(String line, String pattern) {
    Pattern dictionaryEntityPattern = Pattern.compile(pattern);
    Matcher dictionaryEntityMatcher = dictionaryEntityPattern.matcher(line);
    if (!dictionaryEntityMatcher.matches()) {
      parsingErrors.add(ParserSettings.MSG_WRONG_LINE_FORMAT + String.valueOf(currentLineNumber));
      return false;
    }
    return true;
  }

  private String getWord(String line) {
    return getSubstringForPattern(line,ParserSettings.WORD_PATTERN);
  }

  private String getSubstringForPattern(String line, String pattern) {
    Pattern wordPattern = Pattern.compile(pattern);
    Matcher wordMatcher = wordPattern.matcher(line);
    wordMatcher.find();
    return line.substring(wordMatcher.start(), wordMatcher.end());
  }

  private Integer getIntegerValue(String line) {
    String value = getSubstringForPattern(line, ParserSettings.INT_VALUE_PATTERN);
    return Integer.parseInt(value);
  }
}

