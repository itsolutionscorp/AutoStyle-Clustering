#!/usr/bin/env python3
from datetime import date
from calendar import Calendar, day_name, weekday

def meetup_day(year, month, daystr, pos):
	cal = Calendar()
	# list of days by wanted weekday
	days = [day for day in cal.itermonthdays(year, month) if day > 0 and day_name[weekday(year, month, day)] == daystr]
	if pos == 'last':
		return date(year, month, days[-1])
	elif pos[0].isdigit():
		return date(year, month, days[int(pos[0])-1])
	elif pos == 'teenth':
		return date(year, month, [day for day in days if 12 < day < 20][0])
	else:
		return False
