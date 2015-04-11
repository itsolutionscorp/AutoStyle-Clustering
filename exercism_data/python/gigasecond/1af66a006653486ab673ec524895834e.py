from datetime import timedelta
giga = 10**9

def add_gigasecond(birthdate):
	return birthdate + timedelta(seconds = giga)
