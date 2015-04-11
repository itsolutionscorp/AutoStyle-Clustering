#!/usr/bin/env python
# -*- coding: utf-8 -*-

from calendar import monthrange
import datetime

def meetup_day(year, month, day, occurence):
# I suspect there is a built-in feature of Python to handle this..
	weekdays = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
	candidates = []						

	for a in range(1, max(monthrange(year, month))+1):		
		if datetime.date(year, month, a).weekday() == weekdays[day]:
			candidates.append(datetime.date(year, month, a))
# No need to test every date, when we have identified the first candidate	
		a += 7
		
	if occurence == '1st':
		index = 0
	elif occurence == '2nd':
		index = 1
	elif occurence == '3rd':
		index = 2
	elif occurence == '4th':
		index = 3
	elif occurence == 'last':
		index = -1
	elif occurence == 'teenth':

# we do not need to test every date. Only two of the candidates are possible
		for a in range(1,3):
			if candidates[a].day >= 13 and candidates[a].day <= 19 :
				index = a

	return candidates[index]
