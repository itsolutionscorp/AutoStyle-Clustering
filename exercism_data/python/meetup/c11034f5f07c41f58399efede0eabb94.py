''' meetup.py
	created 29 Sept 2014
	by @jestuber '''

from datetime import date
import calendar

def meetup_day(year, month, day, whichday):
	whichday = whichday.lower()
	day = day.lower()
	weekdays = ('monday',
			'tuesday',
			'wednesday',
			'thursday',
			'friday',
			'saturday',
			'sunday')
	day = weekdays.index(day)
	nth = ('1st','2nd','3rd','4th')

	m = calendar.monthcalendar(year,month)

	if whichday in nth:
		if m[0][day]:
			day = m[0+nth.index(whichday)][day]
		else:
			day = m[1+nth.index(whichday)][day]
		
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
