package ru.bravery_and_stupidity.skbwordpadspring.system_tools;

import java.util.Date;
import java.text.SimpleDateFormat;
import ru.sugarbaron_bicycles.library.log.*;
import ru.sugarbaron_bicycles.library.time.*;

public final class Logger {
  public static final int PREVIOUS_INVOKER_LEVEL = 1;
  private static Log log;

  public static void initLog() {
    if(log != null){
      return;
    }
    Clock  clockUnit = new ClockUnit();
    String logFileName = createLogFileName();
    LogToolkit.createLog(logFileName, clockUnit);
    log = LogToolkit.getLog(logFileName);
  }

  private static String createLogFileName() {
    String dateFormatString = createDateFormatString();
    String logFileName = dateFormatString + ".log";
    return logFileName;
  }

  private static String createDateFormatString() {
    Date currentDate = java.util.Calendar.getInstance().getTime();
    SimpleDateFormat converter = new SimpleDateFormat("yyyy_MM_dd_[HH_mm_ss]");
    return converter.format(currentDate);
  }

  public static void debug(String text, Object... parameters) {
    log.debugForPreviousInvoker(PREVIOUS_INVOKER_LEVEL, text, parameters);
  }

  public static void debugForPreviousInvoker(int invocationLevel, String text, Object... parameters) {
    log.debugForPreviousInvoker(invocationLevel, text, parameters);
  }

  public static void error(String text, Object... parameters) {
    log.errorForPreviousInvoker(PREVIOUS_INVOKER_LEVEL, text, parameters);
  }

  public static void errorForPreviousInvoker(int invocationLevel, String text, Object... parameters) {
    log.errorForPreviousInvoker(invocationLevel, text, parameters);
  }

  public static void warning(String text, Object... parameters) {
    log.warningForPreviousInvoker(PREVIOUS_INVOKER_LEVEL, text, parameters);
  }

  public static void warningForPreviousInvoker(int invocationLevel, String text, Object... parameters) {
    log.warningForPreviousInvoker(invocationLevel, text, parameters);
  }

  public static void exception(Exception exceptionToRegister) {
    log.exceptionForPreviousInvoker(PREVIOUS_INVOKER_LEVEL, exceptionToRegister);
  }

  public static void exceptionForPreviousInvoker(int invocationLevel, Exception exceptionToRegister) {
    log.exceptionForPreviousInvoker(invocationLevel, exceptionToRegister);
  }
}
