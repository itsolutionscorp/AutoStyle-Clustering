      # -*- coding: utf-8 -*-
import datetime
import calendar

weekdays = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
teenth_days = (13,14,15,16,17,18,19)
days_in_a_week = 7

def meetup_day(year, month, str_weekday, which):
	meetup_day = get_calendar_day(year, month, str_weekday, which)
	 
	return datetime.date(year, month, meetup_day)

#getting first day of month as int
def first_monthday(str_weekday, year, month):
	#weekday as int
	weekday = weekdays.index(str_weekday)

	#first weekday of month
	first_weekday = calendar.monthrange(year, month)[0]
	if(weekday > first_weekday):
		return 1 + weekday - first_weekday
	elif(weekday < first_weekday):
		return 1 + weekday - first_weekday + days_in_a_week
	#equal
	else:
		return 1

def get_calendar_day(year, month, str_weekday, which):
	monthday = first_monthday(str_weekday, year, month)
	if which == "1st":
		return monthday

	elif which == "2nd":
		return monthday + days_in_a_week

	elif which == "3rd":
		return monthday + 2*days_in_a_week

	elif which == "4th":
		 return monthday + 3*days_in_a_week

	elif which == "last":
		unused, days_in_month = calendar.monthrange(year, month)
		if monthday + 4*days_in_a_week <= days_in_month:
			return monthday + 4*days_in_a_week
		else:
			#return fourth week 
			return monthday + 3*days_in_a_week

	elif which == "teenth":
		if monthday+days_in_a_week in teenth_days:
			return monthday + days_in_a_week
		else:
			return monthday + 2*days_in_a_week

	
