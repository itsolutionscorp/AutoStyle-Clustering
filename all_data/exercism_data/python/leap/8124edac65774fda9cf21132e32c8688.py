# -*- coding: utf-8 -*-

def is_leap_year(year):
	#check if year is divisible by 4
	if year % 4 == 0:
		if year % 100 == 0 and year % 400 != 0:
			return False
		else:
			return True

	else:
		return False
