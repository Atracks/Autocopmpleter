package ru.bravery_and_stupidity.skbwordpadspring.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileParserTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void readData() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/dictionary.in");
    parser.readData();
    Assert.assertEquals(parser.getDictionary().size(), 100000);
    Assert.assertEquals(parser.getInputWords().size(), 15000);
  }

  @Test
  public void doubleReadData() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/dictionary.in");
    parser.readData();
    parser.readData();
    Assert.assertEquals(parser.getDictionary().size(), 100000);
    Assert.assertEquals(parser.getInputWords().size(), 15000);
  }

  @Test
  public void countOfDictionaryMissing() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/countOfDictionaryMissing.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(1));
  }

  @Test
  public void tooMuchSpacers() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/tooMuchSpacers.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(4));
  }

  @Test
  public void wrongCountOfDictionaryGreater() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/wrongCountOfDictionaryGrater.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(7));
  }

  @Test
  public void wrongCountOfDictionaryLess() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/wrongCountOfDictionaryLess.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(4));
  }

  @Test
  public void countOfInputWordsMissing() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/countOfInputWordsMissing.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(5));
  }

  @Test
  public void wrongCountOfInputWordsGrater() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/wrongCountOfInputWordsGrater.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_NEXT_LINE_EXPECTED +
                        String.valueOf(11));
  }

  @Test
  public void wrongCountOfInputWordsLess() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/wrongCountOfInputWordsLess.in");
    parser.readData();
    Assert.assertFalse(parser.hasErrors());
  }

  @Test
  public void fileNotFound() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/wrongFileName.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_FILE_NOT_FOUND);
  }

  @Test
  public void manyErrors() throws Exception {
    FileParser parser = new FileParser("./src/test/java/ru/bravery_and_stupidity/skbwordpadspring/parser/inputDataTests/manyErrors.in");
    parser.readData();
    Assert.assertTrue(parser.hasErrors());
    Assert.assertEquals(parser.getParsingErrors().get(0),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(4));
    Assert.assertEquals(parser.getParsingErrors().get(1),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(6));
    Assert.assertEquals(parser.getParsingErrors().get(2),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(7));
    Assert.assertEquals(parser.getParsingErrors().get(3),
                        ParserSettings.MSG_WRONG_LINE_FORMAT +
                        String.valueOf(8));
  }
}
