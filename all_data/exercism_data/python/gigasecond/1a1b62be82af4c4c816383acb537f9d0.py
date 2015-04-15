def add_gigasecond(birthDate):
	from datetime import date
	from datetime import timedelta

	return birthDate + timedelta(seconds=1000000000) 
