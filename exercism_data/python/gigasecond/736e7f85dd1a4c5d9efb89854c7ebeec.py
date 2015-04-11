# -*- coding: utf-8 -*-
'''
Gigasecond.

Author: SprayIdle

This program calculates the date that someone turned or will celebrate
their 1 Gs anniversary

TESTING:

	gigasecond.py (seperate file) will run numerous test cases
	over the gigasecond module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of add_gigasecond function.
'''

from datetime import timedelta

Gs = timedelta(seconds=10**9)

def add_gigasecond(birthday):
	'''(date) -> date
	
	Return the date that someone turned or will celebrate their 1 Gs
	anniversary
	'''
	return birthday + Gs
