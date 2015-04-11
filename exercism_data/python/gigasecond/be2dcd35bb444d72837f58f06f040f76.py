from datetime import date, timedelta

def add_gigasecond(date):
	gigasec = timedelta(seconds=1e9)
	bday = date + gigasec
	return bday
