#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

import calendar
from datetime import date

week_dict = {
	'Monday'   : 0,
	'Tuesday'  : 1,
	'Wednesday': 2,
	'Thursday' : 3,
	'Friday'   : 4,
	'Saturday' : 5,
	'Sunday'   : 6
}

desc_dict = {
	'1st'   : 0,
	'2nd'   : 1,
	'3rd'   : 2,
	'4th'   : 3,
	'last'  : -1
}

def meetup_day(year, month, weekday, descriptor):
	weekday_num = week_dict[weekday]
	day_list = [x[weekday_num] for x in calendar.monthcalendar(year, month) if x[weekday_num] != 0]
	
	if descriptor == 'teenth':
		day = return_teen(day_list)
	else:
		desc_num = desc_dict[descriptor]
		day = day_list[desc_num]
	
	return date(year, month, day)

def return_teen(day_list):
	for y in day_list:
		if y >= 13 and y < 20:
			return y
