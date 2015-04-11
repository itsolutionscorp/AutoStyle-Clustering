import datetime

def add_gigasecond(oldDate):

	daysToAdd = 1000000000/(3600*24)

	newDate = oldDate + datetime.timedelta(days=daysToAdd)

	return newDate
