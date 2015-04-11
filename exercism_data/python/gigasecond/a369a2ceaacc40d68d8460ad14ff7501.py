from datetime import datetime, timedelta

def add_gigasecond(date):
	return date + timedelta(0, 1000000000) # It made me crazy when I figured out that a page of code I had written could be accomplished in this one line
