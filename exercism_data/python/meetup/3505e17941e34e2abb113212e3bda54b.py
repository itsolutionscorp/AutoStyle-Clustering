import datetime
import calendar

def meetup_day(year, month, day, meetuptype):

# Converts day of the week to a number

	if day == 'Monday':
		weekday = 1
	elif day == 'Tuesday':
		weekday = 2
	elif day == 'Wednesday':
		weekday = 3
	elif day == 'Thursday':
		weekday = 4
	elif day == 'Friday':
		weekday = 5
	elif day == 'Saturday':
		weekday = 6
	elif day == 'Sunday':
		weekday = 7

	if meetuptype == 'teenth':
		for x in range(13, 20):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)

	if meetuptype == '1st':
		for x in range(1, 8):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)

	if meetuptype == '2nd':
		for x in range(8, 15):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)

	if meetuptype == '3rd':
		for x in range(15, 22):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)

	if meetuptype == '4th':
		for x in range(22, 29):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)

# Uses calendar function to find end of the month. Loops through range in reverse to find the last day satisfying the conditions

	if meetuptype == 'last':
		for x in reversed(range(calendar.monthrange(year, month)[1]-7, calendar.monthrange(year, month)[1]+1)):
			if datetime.date(year, month, x).isoweekday() == weekday:
				return datetime.date(year, month, x)
