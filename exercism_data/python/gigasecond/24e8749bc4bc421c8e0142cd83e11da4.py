from datetime import timedelta

def add_gigasecond(birthDate):
	birthDate += timedelta(seconds=10**9)
	return birthDate
