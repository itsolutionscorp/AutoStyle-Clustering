#!/usr/bin/python
import math

def is_leap_year(test_year):
	if math.fmod(test_year,100)==0 and math.fmod(test_year,400)!=0:
		return False
	elif math.fmod(test_year,4)==0:
		return True
	return False
