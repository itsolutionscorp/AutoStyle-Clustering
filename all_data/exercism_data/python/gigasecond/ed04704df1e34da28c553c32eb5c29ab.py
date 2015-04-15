import time
from datetime import timedelta

def add_gigasecond(startingDate):
	endingDate = startingDate + timedelta(seconds=(10**9))
	return endingDate
