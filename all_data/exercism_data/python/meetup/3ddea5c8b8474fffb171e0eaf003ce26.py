import datetime
from datetime import timedelta
from year import is_leap_year

daysOfTheWeek = { 
	'Monday'    : 0,
	'Tuesday'   : 1,
	'Wednesday' : 2,
	'Thursday'  : 3,
	'Friday'    : 4,
	'Saturday'  : 5,
	'Sunday'    : 6
	}
#holds the amount of days in a month
#where 0 = Jan, 1 = Feb, etc.
daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
daysInMonthLeap = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

#returns a timedelta of the amount of days date is away from weekday
def daysTilWeekday(date, weekday):
	return timedelta(days = (daysOfTheWeek[weekday] - date.weekday()) % 7) 


def meetup_day(year, month, weekday, week):
	
	assert week in ['1st', '2nd', '3rd', '4th', 'last', 'teenth'], 'Not a valid week choice'

	if week == '1st':
		date = datetime.date(year, month, 1)
		return date + daysTilWeekday(date, weekday)

	elif week == '2nd':
		date = datetime.date(year, month, 8)
		return date + daysTilWeekday(date, weekday)

	elif week == '3rd':
		date = datetime.date(year, month, 15)
		return date + daysTilWeekday(date, weekday)

	elif week == '4th':
		date = datetime.date(year, month, 22)
		return date + daysTilWeekday(date, weekday)

	elif week == 'last':
		if not is_leap_year(year):
			date = datetime.date(year, month, daysInMonth[month - 1]) 
		else:
			date = datetime.date(year, month, daysInMonthLeap[month - 1])
		
		lastWeekday = date.weekday()
		daysAfterMeetup = timedelta(days = (lastWeekday - daysOfTheWeek[weekday]) % 7)
		return date - daysAfterMeetup
	
	elif week == 'teenth':
		date = datetime.date(year, month, 13)
		return date + daysTilWeekday(date, weekday)
