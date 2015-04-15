from datetime import timedelta

def add_gigasecond(birthDate):
	gigaSecondDate = birthDate + timedelta(seconds=10**9)
	return gigaSecondDate
