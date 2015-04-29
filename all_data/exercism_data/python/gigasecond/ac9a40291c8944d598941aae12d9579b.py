#!/usr/bin/python
from datetime import date
from datetime import timedelta

def add_gigasecond(d1):
	d2 = d1+timedelta(seconds=10**9)
	return d2	
