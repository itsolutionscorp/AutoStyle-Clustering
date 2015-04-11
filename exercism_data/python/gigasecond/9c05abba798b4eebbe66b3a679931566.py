from dateutil.relativedelta import relativedelta
def add_gigasecond(date):
	daysInGigaSec = int(10**9/(60*60*24))
	return date + relativedelta(days=daysInGigaSec)
