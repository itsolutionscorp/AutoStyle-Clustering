#!/usr/bin/env python

import datetime

def add_gigasecond(date):
	gigasecond = datetime.timedelta(seconds=10**9)
	return date + gigasecond 
