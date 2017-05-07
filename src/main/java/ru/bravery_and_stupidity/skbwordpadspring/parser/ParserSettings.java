package ru.bravery_and_stupidity.skbwordpadspring.parser;


final public class ParserSettings {
  final static String MSG_WRONG_LINE_FORMAT = "Wrong line format, line = ";
  final static String MSG_INT_VALUE_EXPECTED = "Integer value expected, line = ";
  final static String MSG_NEXT_LINE_EXPECTED = "Next line expected, line = ";
  final static String MSG_FILE_NOT_FOUND = "File not found";
  final static String DICTIONARY_ENTITY_PATTERN = "[a-zA-Z]+ [0-9]+";
  final static String WORD_PATTERN = "[a-zA-Z]+";
  final static String INT_VALUE_PATTERN = "[0-9]+";

  private ParserSettings() {}
}
