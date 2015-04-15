from datetime import date,timedelta
def add_gigasecond(bdate):
	dur=timedelta(seconds=10**9)
	#timedelta stores the difference between two datetime values
	return bdate+dur
