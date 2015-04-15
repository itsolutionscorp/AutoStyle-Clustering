#!/usr/bin/python
# -*- coding: utf-8 -*-

def is_leap_year(year):
	"""return boolean indicating if year is a leap year"""
	if (year % 400 == 0 and year % 100 == 0):
		return True
	elif (year % 400 != 0 and year % 100 == 0):
		return False
	elif (year % 4 == 0):
		return True
	else:
		return False
