import datetime

def add_gigasecond(inDate):
	gigasecond = (10**9)
	toAdd = datetime.timedelta(seconds = gigasecond)
	return inDate + toAdd
