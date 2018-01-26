package com.craftsman.privatescheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class TimeService {
	List<DateTime> holidays = new ArrayList<DateTime>();

	public boolean isHoliday(DateTime dateTime) {
		if ( dateTime.getDayOfWeek() == DateTimeConstants.SATURDAY )
			return true;
		
		if ( dateTime.getDayOfWeek() == DateTimeConstants.SUNDAY )
			return true;
		
		DateTime dateAtMidnight = dateTime.withTimeAtStartOfDay();
		
		for ( DateTime holiday : holidays ) {
			DateTime dateHoliday = holiday.withTimeAtStartOfDay();
			if ( dateHoliday.equals(dateAtMidnight) )
				return true;
		}
		return false;
	}

}
