import datetime

def add_gigasecond(birthDate):
	gigaDate = birthDate + datetime.timedelta(seconds=1*10**9)
	return gigaDate
