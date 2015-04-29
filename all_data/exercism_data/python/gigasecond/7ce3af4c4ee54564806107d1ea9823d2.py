from datetime import date, timedelta

def add_gigasecond(date_object):
# Takes a date object and returns a date which is 
# 1 billion seconds later
	return date_object + timedelta(seconds = 10**9) 
	
