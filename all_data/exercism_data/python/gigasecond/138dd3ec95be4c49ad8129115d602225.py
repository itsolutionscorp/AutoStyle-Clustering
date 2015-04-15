#!/usr/bin/python
import datetime

def add_gigasecond(date):

	gigasecond = datetime.timedelta(seconds=10**9)
	gigadate = date + gigasecond

	return gigadate
