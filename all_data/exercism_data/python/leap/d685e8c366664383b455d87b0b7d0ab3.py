#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def is_leap_year(year):
	if not isinstance(year, int):
		raise ValueError("year must be an integer")
	
	if year % 4 == 0 and (year % 100 != 0 or year % 400 == 0):
		return True
	
	return False
