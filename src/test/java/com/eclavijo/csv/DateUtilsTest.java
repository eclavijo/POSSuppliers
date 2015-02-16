package com.eclavijo.csv;
import junit.framework.TestCase; 

import org.junit.Test; 

import com.eclavijo.DateUtils;


public class DateUtilsTest extends TestCase { 
	
	
  @Test 
  public void testGetNum() { 
        assertEquals(29, DateUtils.ageCalculator(1985,10,3,2015,1,23));
        assertEquals(18, DateUtils.ageCalculator(1996,24,2,2015,1,23));

  } 
}