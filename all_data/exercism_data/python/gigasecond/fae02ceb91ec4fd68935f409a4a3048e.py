import datetime

def add_gigasecond(birthdate):
	gs = datetime.timedelta(0,10**9)
	return birthdate + gs
