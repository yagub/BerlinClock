package com.ubs.opsit.interviews;

/**
 * Implementation of {@link TimeConverter} interface.
 * <p>
 * Created by antonguba on 2/10/2016.
 */
public class TimeConverterImpl implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
	  if (!TimeValidator.isValid(aTime)) {
      throw new IllegalArgumentException("invalid time format. " +
              "Expected string in 24 hour format: hh:mm:ss. " +
              "Regexp: " + TimeValidator.TIME_FORMAT_REGEXP);
	  }
	  String[] time = aTime.split(":");
	  String res = getSeconds(Integer.parseInt(time[2])) + System.lineSeparator() + 
        getTopHours(Integer.parseInt(time[0])) + System.lineSeparator() +
        getBottomHours(Integer.parseInt(time[0])) + System.lineSeparator() + 
        getTopMinutes(Integer.parseInt(time[1])) + System.lineSeparator() + 
        getBottomMinutes(Integer.parseInt(time[1])); 
	  return res;
	}
	
	protected String getSeconds(int number) {
    if (number % 2 == 0) 
      return "Y";
    return "O";
  }
  
  protected String getTopHours(int number) {
      return getOnOff(4, getTopNumberOfOnLights(number));
  }
  
  protected String getBottomHours(int number) {
      return getOnOff(4, number % 5);
  }
  
  protected String getTopMinutes(int number) {
      return getOnOff(11, getTopNumberOfOnLights(number), "Y").replaceAll("YYY", "YYR");
  }
  
  protected String getBottomMinutes(int number) {
      return getOnOff(4, number % 5, "Y");
  }
  
  private String getOnOff(int lamps, int onSigns) {
      return getOnOff(lamps, onSigns, "R");
  }
  private String getOnOff(int lamps, int onSigns, String onSign) {
      String out = "";
      for (int i = 0; i < onSigns; i++)
          out += onSign;
      for (int i = 0; i < (lamps - onSigns); i++)
          out += "O";
      return out;
  }
  
  private int getTopNumberOfOnLights(int number) {
      return (number - (number % 5)) / 5;
  }
	
}
