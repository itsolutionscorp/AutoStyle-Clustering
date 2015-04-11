#Calculate Gigasecond Anniversary

from datetime import date, timedelta

def add_gigasecond(x):
	giga = x + timedelta(seconds=10**9)
	return giga

