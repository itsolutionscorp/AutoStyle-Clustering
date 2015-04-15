import datetime
import time

def add_gigasecond(date):
	placeholder_seconds = datetime.datetime.min.time()
	full_time = datetime.datetime.combine(date,placeholder_seconds)
	gigasecond= datetime.timedelta(0,10**9)

	gig_date = full_time + gigasecond
	return gig_date.date()
