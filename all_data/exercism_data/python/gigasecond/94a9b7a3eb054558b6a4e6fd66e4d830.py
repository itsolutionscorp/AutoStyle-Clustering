from datetime import datetime, timedelta
def add_gigasecond(date1):
	gigasec = timedelta(seconds=10**9)
	return date1 + gigasec
