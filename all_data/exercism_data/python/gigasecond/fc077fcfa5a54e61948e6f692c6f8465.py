from datetime import timedelta
from datetime import date

def add_gigasecond(bday):
	giga = bday + timedelta(seconds=1000000000)
	return giga
	
