package com.ubs.opsit.interviews;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * JUnit tests
 */
public class BerlinClockTest {
	private TimeConverterImpl berlinClock = new TimeConverterImpl();
	
	// Yellow lamp should blink on/off every two seconds
  @Test
  public void yellowLampShouldBlinkEveryTwoSeconds() {
      assertEquals("Y", berlinClock.getSeconds(0));
      assertEquals("O", berlinClock.getSeconds(1));
      assertEquals("O", berlinClock.getSeconds(59));
  }

  // Top hours should have 4 lamps
  @Test
  public void topHoursShouldHave4Lamps() {
      assertEquals(4, berlinClock.getTopHours(7).length());
  }

  // Top hours should light a red lamp for every 5 hours
  @Test
  public void topHoursShouldLightRedLampForEvery5Hours() {
      assertEquals("OOOO", berlinClock.getTopHours(0));
      assertEquals("RROO", berlinClock.getTopHours(13));
      assertEquals("RRRR", berlinClock.getTopHours(23));
      assertEquals("RRRR", berlinClock.getTopHours(24));
  }

  // Bottom hours should have 4 lamps
  @Test
  public void bottomHoursShouldHave4Lamps() {
      assertEquals(4, berlinClock.getBottomHours(5).length());
  }

  // Bottom hours should light a red lamp for every hour left from top hours
  @Test
  public void bottomHoursShouldLightRedLampForEveryHourLeftFromTopHours() {
      assertEquals("OOOO", berlinClock.getBottomHours(0));
      assertEquals("RRRO", berlinClock.getBottomHours(13));
      assertEquals("RRRO", berlinClock.getBottomHours(23));
      assertEquals("RRRR", berlinClock.getBottomHours(24));
  }

  // Top minutes should have 11 lamps
  @Test
  public void topMinutesShouldHave11Lamps() {
      assertEquals(11, berlinClock.getTopMinutes(34).length());
  }

  // Top minutes should have 3rd, 6th and 9th lamps in red to indicate first quarter, half and last quarter
  @Test
  public void topMinutesShouldHave3rd6thAnd9thLampsInRedToIndicateFirstQuarterHalfAndLastQuarter() {
      String minutes32 = berlinClock.getTopMinutes(32);
      assertEquals("R", minutes32.substring(2, 3));
      assertEquals("R", minutes32.substring(5, 6));
      assertEquals("O", minutes32.substring(8, 9));
  }

  // Top minutes should light a yellow lamp for every 5 minutes unless it's first quarter, half or last quarter
  @Test
  public void topMinutesShouldLightYellowLampForEvery5MinutesUnlessItIsFirstQuarterHalfOrLastQuarter() {
      assertEquals("OOOOOOOOOOO", berlinClock.getTopMinutes(0));
      assertEquals("YYROOOOOOOO", berlinClock.getTopMinutes(17));
      assertEquals("YYRYYRYYRYY", berlinClock.getTopMinutes(59));
  }

  // Bottom minutes should have 4 lamps
  @Test
  public void bottomMinutesShouldHave4Lamps() {
      assertEquals(4, berlinClock.getBottomMinutes(0).length());
  }

  // Bottom minutes should light a yellow lamp for every minute left from top minutes
  @Test
  public void bottomMinutesShouldLightYellowLampForEveryMinuteLeftFromTopMinutes() {
      assertEquals("OOOO", berlinClock.getBottomMinutes(0));
      assertEquals("YYOO", berlinClock.getBottomMinutes(17));
      assertEquals("YYYY", berlinClock.getBottomMinutes(59));
  }

  // Berlin Clock should result in array with 5 elements
  @Test
  public void berlinClockShouldResultInArrayWith5Elements()  {
      assertEquals(5, berlinClock.convertTime("13:17:01").split(System.lineSeparator()).length);
  }

  // Berlin Clock should result in correct seconds, hours and minutes
  @Test
  public void berlinClockShouldResultInCorrectSecondsHoursAndMinutes() {
      String[] berlinTime = berlinClock.convertTime("16:37:16").split(System.lineSeparator());
      String[] expected = new String[] {"Y", "RRRO", "ROOO", "YYRYYRYOOOO", "YYOO"};
      assertArrayEquals(berlinTime, expected);
  }
  
  // Berlin Clock should throw IllegalArgumentException
  @Test(expected = IllegalArgumentException.class)
  public void givenNonDigitBerlinClockShouldResultInThrowException() {
      berlinClock.convertTime("aa:00:00");
  }

  // Berlin Clock should throw IllegalArgumentException
  @Test(expected = IllegalArgumentException.class)
  public void given25HoursBerlinClockShouldResultInThrowException() {
      berlinClock.convertTime("25:00:00");
  }
  
  // Berlin Clock should throw IllegalArgumentException
  @Test(expected = IllegalArgumentException.class)
  public void givenNegativeHoursBerlinClockShouldResultInThrowException() {
      berlinClock.convertTime("-1:00:00");
  }
  
 // Berlin Clock should throw IllegalArgumentException
 @Test(expected = IllegalArgumentException.class)
 public void givenEmptyStringBerlinClockShouldResultInThrowException() {
     berlinClock.convertTime("");
 }

 // Berlin Clock should throw IllegalArgumentException
 @Test(expected = IllegalArgumentException.class)
 public void givenNullBerlinClockShouldResultInThrowException() {
   berlinClock.convertTime(null);
 }
 
}
