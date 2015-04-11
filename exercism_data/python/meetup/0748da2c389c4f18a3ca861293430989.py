# -*- coding: utf-8 -*-
'''
Meetup.

Author: SprayIdle

This program calculates the date of meetups.

Typically meetups happen on the same day of the week.

Examples are

- the first Monday
- the third Tuesday
- the Wednesteenth
- the last Thursday

There are exactly 7 days that end in '-teenth'. Therefore, one is
guaranteed that each day of the week will have a '-teenth' in every
month.

TESTING:

	meetup_test.py (seperate file) will run numerous test cases
	over the meetup module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of meetup_day function.
'''

from datetime import date, timedelta

days_of_week = {
	'Monday': 1,
	'Tuesday': 2,
	'Wednesday': 3,
	'Thursday': 4,
	'Friday': 5,
	'Saturday': 6,
	'Sunday': 7
	}

def meetup_day(year, month, day_of_week, suffix):
	'''(int, int, string, string) -> date
	
	Return the date of meetup given by year, month, day and suffix where
	day is long form string 'Monday' -> 'Sunday' and suffix is '1st',
	'2nd', '3rd', '4th', 'last' or 'teenth'
	'''
	day_delta = timedelta(days=1)
	day_of_week_int = days_of_week[day_of_week]
	working_date = date(year=year,month=month,day=1)
	# If looking for last day of the month, jump ahead and work backwards
	if suffix.startswith('l'):
		month += 1
		day_delta = timedelta(days=-1)
		working_date = date(year=year,month=month,day=1) + day_delta
	# Get to correct day of the week
	while working_date.isoweekday() is not day_of_week_int:
		working_date += day_delta
	# Get to the correct week
	if suffix.startswith(('1', '2', '3', '4')):
		week = int(suffix[0]) - 1
		working_date += day_delta * week * 7
	elif suffix.startswith('t'):
		while working_date.day < 13:
			working_date += day_delta * 7
	
	return working_date
