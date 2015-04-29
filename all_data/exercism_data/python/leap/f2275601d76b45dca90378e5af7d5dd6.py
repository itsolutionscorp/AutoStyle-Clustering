# -*- coding: utf-8 -*-
'''
Leap year.

Author: SprayIdle

This program determines whether a given year is a leap year.
A leap year occurs on any year that is evenly divisible by 4,
except every year is evenly divisible by 100 unless the year
is also evenly divisible by 400

TESTING:

	leap_test.py (seperate file) will run numerous test cases over the 
	leap module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of leap function.
'''

def is_leap_year(year):
	'''(int) -> bool
	
	Return a bool indicating if the input year is a leap year
	'''
	return year % 4 == 0 and not year % 100 == 0 or year % 400 == 0 
	
