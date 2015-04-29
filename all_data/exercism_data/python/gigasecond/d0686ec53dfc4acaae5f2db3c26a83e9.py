from datetime import timedelta

def add_gigasecond(inDate):
	gigasecond = (10**9)
	toAdd = timedelta(seconds = gigasecond)
	return inDate + toAdd
