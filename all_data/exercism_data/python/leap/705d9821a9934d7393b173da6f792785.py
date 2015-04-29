# -*- coding: utf-8 -*-
def is_leap_year(year):
	if not(year % 400):
		return True
	elif not(year % 100):
		return False
	elif not(year % 4):
		return True
	else:
		return False
