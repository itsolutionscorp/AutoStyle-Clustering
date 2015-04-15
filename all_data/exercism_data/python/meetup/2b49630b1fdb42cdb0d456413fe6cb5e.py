''' meetup.py
	created 29 Sept 2014
	by @jestuber '''

from datetime import date
import calendar

def meetup_day(year, month, day, whichday):
	whichday = whichday.lower()
	day = day.lower()
	weekdays = {'monday':0,
			'tuesday':1,
			'wednesday':2,
			'thursday':3,
			'friday':4,
			'saturday':5,
			'sunday':6}
	day = weekdays[day]

	m = calendar.monthcalendar(year,month)

	if whichday == '1st':
		if m[0][day]:
			day = m[0][day]
		else:
			day = m[1][day]
		
		return date(year, month, day)

	elif whichday == '2nd':
		if m[0][day]:
			day = m[1][day]
		else:
			day = m[2][day]
		
		return date(year, month, day)


	elif whichday == '3rd':
		if m[0][day]:
			day = m[2][day]
		else:
			day = m[3][day]
		
		return date(year, month, day)

	elif whichday == '4th':
		if m[0][day]:
			day = m[3][day]
		else:
			day = m[4][day]
		
		return date(year, month, day)

	elif whichday == 'last':
		if m[-1][day]:
			day = m[-1][day]
		else:
			day = m[-2][day]
		
		return date(year, month, day)

	elif whichday == 'teenth':
		for week in range(0,6):
			if m[week][day] in range(13, 20):
				day = m[week][day]
				return date(year, month, day)
