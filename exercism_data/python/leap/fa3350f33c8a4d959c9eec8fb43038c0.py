#!/usr/bin/python
# -*- coding: utf-8 -*-
def is_leap_year(year):
	if year % 4 == 0:
		if year % 100 == 0:
			if year % 400 == 0:
				return True
			else:
				return False
		return True
	return False