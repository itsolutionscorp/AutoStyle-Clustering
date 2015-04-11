#!/usr/bin/python

""" Leap Years occur
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
"""

def is_leap_year(year):
	leap_year = False
	if year % 4 == 0:
		leap_year = True
		if year % 100 == 0 and year % 400 != 0:
			leap_year = False
	return leap_year
