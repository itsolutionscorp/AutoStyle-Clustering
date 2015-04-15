from datetime import datetime
from datetime import date
import calendar

def meetup_day(year, month, meetupDay, dayInMonth):
	daysOfWeek 	= {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
	meetupDay 	= daysOfWeek[meetupDay]
	tempDate 	= datetime(year, month, 13)
	tempDay 	= tempDate.day
	daysAway 	= 0
	cal 		= calendar.Calendar()
	monthDays 	= cal.itermonthdays2(year, month)
	
	if dayInMonth == 'last':
		for i, day in reversed(list(enumerate(monthDays))):
			if day[0] != 0 and day[1] == meetupDay:
		   		return date(year, month, day[0])
	elif dayInMonth == 'teenth':
		if tempDay < meetupDay:
			daysAway = meetupDay-tempDay
		else:
			daysAway = 7-tempDay+meetupDay
		return tempDate + timedelta(days=daysAway)
	else:
		dayInMonth=int(dayInMonth[0])
		dayCount=0
		for i, day in monthDays:
			if day[0] != 0 and day[1] == dayInMonth:
				dayCount+=1
			if dayCount == dayInMonth:
				return date(year, month, day[0])
