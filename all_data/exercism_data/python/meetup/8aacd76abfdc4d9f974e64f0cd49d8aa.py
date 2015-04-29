# -*- coding: utf-8 -*-
import datetime
import calendar

weekdays = ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')
teenth_days = (13,14,15,16,17,18,19)
int_week = 7

def meetup_day(year, month, weekday, which):
	return datetime.date(year, month, get_calendar_day(year, month, weekday, which))

#getting first day of month as int
def first(weekday, year, month):
	#weekday as int
	int_weekday = weekdays.index(weekday)

	#first weekday of month
	first_weekday = calendar.monthrange(year, month)[0]
	if(int_weekday > first_weekday):
		return 1 + int_weekday - first_weekday
	elif(int_weekday < first_weekday):
		return 1 + int_weekday - first_weekday + int_week
	#equal
	else:
		return 1

def get_calendar_day(year, month, weekday, which):
	if which == "1st":
		return first(weekday, year, month)

	elif which == "2nd":
		return first(weekday, year, month)+int_week

	elif which == "3rd":
		return first(weekday, year, month)+2*int_week

	elif which == "4th":
		return first(weekday, year, month)+3*int_week

	elif which == "last":
		if(first(weekday, year, month) + 4*int_week <= calendar.monthrange(year, month)[1]):
			return first(weekday, year, month) + 4*int_week
		else:
			#return fourth week 
			return first(weekday, year, month)+3*int_week

	elif which == "teenth":
		first_day = first(weekday, year, month)
		if first_day+int_week in teenth_days:
			return first_day+int_week
		else:
			return first_day+2*int_week
