from datetime import timedelta

gigasecond = 10**9

def add_gigasecond(dateobj):
# kudos to @cdroulers
	return dateobj + timedelta(0,gigasecond)
