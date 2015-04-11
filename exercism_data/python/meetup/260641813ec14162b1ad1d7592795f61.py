#!/usr/bin/env python
 # -*- coding: utf-8 -*-

from datetime import date, timedelta
import calendar

DAYS = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]

def meetup_day(year, month, week_day, cardinal):	
	if cardinal == "last" :
		last_day_of_month = calendar.monthrange(year,month)[1]
		return date(year,month,last_day_of_month)
	elif cardinal == "teenth":
		n = 1
		date_i = date(year, month, 10)
	else:
		cardinal_map = {"1st" : 1, "2nd" : 2, "3rd" : 3, "4th" : 4}
		n = cardinal_map[cardinal]
		date_i = date(year, month, 1)
	
	i = 0 
	while(True):
		if DAYS[date_i.toordinal() % 7 ] == week_day:
			i +=1
			if i == n:
				break
			
		date_i += timedelta(1)
	
	return date_i
