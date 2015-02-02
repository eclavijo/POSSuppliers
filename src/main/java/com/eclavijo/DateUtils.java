package com.eclavijo;

import java.util.Date;

public class DateUtils {

	public Date getCurrentDate() {
		return new Date();
	}

	static int ageCalculator(int birthYear, int birthMonth, int birthDay,
			int curYear, int curMonth, int curDay) {
		String answer;
		int current = curYear * 10000 + curMonth * 100 + curDay;
		int birth = birthYear * 10000 + birthMonth * 100 + birthDay;
		answer = Integer.toString(current - birth);
		answer = answer.substring(0, 2);
		return (Integer.parseInt(answer));
	}

}