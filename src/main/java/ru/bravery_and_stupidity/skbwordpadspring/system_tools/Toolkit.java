package ru.bravery_and_stupidity.skbwordpadspring.system_tools;

import ru.sugarbaron_bicycles.library.exceptions.CriticalOperationFailed;
import ru.sugarbaron_bicycles.library.exceptions.NeedFixCode;

public final class Toolkit {
  public static void requireNotNull(Object referenceToCheck) {
    if(null == referenceToCheck){
      throwNeedFixCode("unexpected null value");
    }
    return;
  }

  public static void requireNotEmpty(String stringToCheck) {
    if(stringToCheck.isEmpty() ) {
      throwNeedFixCode("unexpected empty string");
    }
    return;
  }

  public static void throwNeedFixCode(String message) {
    NeedFixCode needFixCode = new NeedFixCode(message);
    Logger.exception(needFixCode);
    throw needFixCode;
  }

  public static void throwCriticalOperationFailed(String message) {
    CriticalOperationFailed criticalOperationFailed = new CriticalOperationFailed(message);
    Logger.exception(criticalOperationFailed);
    throw criticalOperationFailed;
  }

}