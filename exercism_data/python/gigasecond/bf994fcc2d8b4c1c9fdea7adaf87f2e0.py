from datetime import datetime
from datetime import timedelta

def add_gigasecond(initial_date):
	gigasecond = 10**9
	return initial_date + timedelta(seconds = gigasecond)

print add_gigasecond(datetime(2011, 4, 25))
