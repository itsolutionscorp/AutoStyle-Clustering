from datetime import date, timedelta

def add_gigasecond(date):
	gigasec = timedelta(seconds=1e9).days
	bday = date.fromordinal(date.toordinal()+gigasec)
	return bday
