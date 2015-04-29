def add_gigasecond(birthDate):
	from datetime import timedelta
	return birthDate + timedelta(seconds=10**9)
