#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def is_leap_year(year):
	if not isinstance(year, int):
		raise ValueError("year must be an integer")
	
	return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
