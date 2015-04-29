# -*- coding: utf-8 -*-
import datetime
import calendar

weekdays = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
teenth_days = (13,14,15,16,17,18,19)
int_week = 7

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
		return 1 + weekday - first_weekday + int_week
	#equal
	else:
		return 1

def get_calendar_day(year, month, str_weekday, which):
	delta_day = None
	if which == "1st":
		delta_day = 0

	elif which == "2nd":
		delta_day = int_week

	elif which == "3rd":
		delta_day = 2*int_week

	elif which == "4th":
		 delta_day = 3*int_week

	elif which == "last":
		if(first_monthday(str_weekday, year, month) + 4*int_week <= calendar.monthrange(year, month)[1]):
			delta_day = 4*int_week
		else:
			#return fourth week 
			delta_day = 3*int_week

	elif which == "teenth":
		first_day = first_monthday(str_weekday, year, month)
		if first_day+int_week in teenth_days:
			delta_day = int_week
		else:
			delta_day = 2*int_week

	if(which != None):
		return first_monthday(str_weekday, year, month) + delta_day
	else:
		raise Exception("Undefined option")
