#!/usr/bin/env python
 # -*- coding: utf-8 -*-
 
from datetime import date, datetime, timedelta

GIGASECOND = 10**9

def add_gigasecond(anniversary_date):
	if not isinstance(anniversary_date, date):
		raise ValueError("anniversary_date must be a date")

	gigasecond_anniversary_datetime = datetime.combine(anniversary_date, datetime.min.time())
	gigasecond_anniversary_datetime += timedelta(0,GIGASECOND)
	
	return gigasecond_anniversary_datetime.date()
