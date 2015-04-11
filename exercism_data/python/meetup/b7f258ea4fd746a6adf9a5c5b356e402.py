#!/usr/bin/env python

import datetime

def meetup_day(year, month, weekday, week_description):
	month_start_weekday = datetime.date(year, month, 1).weekday()

	if weekday == 'Monday':
		weekday_number = 0
	elif weekday == 'Tuesday':
		weekday_number = 1
	elif weekday == 'Wednesday':
                weekday_number = 2
	elif weekday == 'Thursday':
                weekday_number = 3
	elif weekday == 'Friday':
                weekday_number = 4
	elif weekday == 'Saturday':
                weekday_number = 5
	else:
		weekday_number = 6
	
	first_of_that_weekday = 1
	#Find the first date of that weekday in the month
	if weekday_number >= month_start_weekday:
		delta_weekday = weekday_number - month_start_weekday
		first_of_that_weekday += delta_weekday
	else:
		delta_weekday = weekday_number +7 - month_start_weekday
		first_of_that_weekday += delta_weekday

	#Need last day month as well
	if month <= 11:
		last_day_month = (datetime.date(year, month +1, 1) - datetime.timedelta(days = 1)).day
	else:
		last_day_month = (datetime.date(year+1, 1, 1) - datetime.timedelta(days = 1)).day

	if week_description == '1st':
		week_number = 0
		return datetime.date(year, month, first_of_that_weekday + week_number*7)
	elif week_description == '2nd':
                week_number = 1
		return datetime.date(year, month, first_of_that_weekday + week_number*7)
	elif week_description == '3rd':
                week_number = 2
		return datetime.date(year, month, first_of_that_weekday + week_number*7)
	elif week_description == '4th':
                week_number = 3
		return datetime.date(year, month, first_of_that_weekday + week_number*7)
	if week_description == 'last':
		meetup_date = last_day_month
                while datetime.date(year, month, meetup_date).weekday() != weekday_number:
			meetup_date -=1
		return datetime.date(year, month, meetup_date)
	elif week_description == 'teenth':
		meetup_date = first_of_that_weekday
		while meetup_date < 13:
			meetup_date += 7
		return datetime.date(year, month, meetup_date)
