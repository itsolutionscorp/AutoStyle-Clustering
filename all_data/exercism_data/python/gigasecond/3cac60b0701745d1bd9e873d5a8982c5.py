from datetime import timedelta

def add_gigasecond(dateobj):
# kudos to @cdroulers
	return dateobj + timedelta(0,10**9)
