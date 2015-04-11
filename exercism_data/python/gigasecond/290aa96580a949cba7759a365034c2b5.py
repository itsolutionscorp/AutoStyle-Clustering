from datetime import datetime, timedelta

def add_gigasecond(birthdate):
	return birthdate + timedelta(seconds=10**9)
