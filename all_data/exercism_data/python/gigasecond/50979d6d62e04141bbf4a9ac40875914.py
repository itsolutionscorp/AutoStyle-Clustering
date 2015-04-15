#!/usr/bin/env python
 # -*- coding: utf-8 -*-
 
from datetime import date, timedelta

GIGASECOND = 10**9

def add_gigasecond(anniversary_date):
	if not isinstance(anniversary_date, date):
		raise ValueError("anniversary_date must be a date")
	
	return anniversary_date + timedelta(seconds=GIGASECOND)
