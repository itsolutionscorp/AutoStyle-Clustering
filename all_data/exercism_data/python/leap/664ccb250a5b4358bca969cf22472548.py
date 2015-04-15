#!/usr/bin/python

def is_leap_year(year):
	#A year is leap year if it is divisible by 4 but not divisible by 100 unless it is divisible by 400
	if year % 400 == 0:
		return True
	if year % 4 == 0 and year % 100 != 0:
		return True

	return False
