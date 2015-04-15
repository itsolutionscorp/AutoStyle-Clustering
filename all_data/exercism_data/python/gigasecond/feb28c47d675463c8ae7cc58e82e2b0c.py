from datetime import timedelta

def add_gigasecond(birth_date):
	return birth_date + timedelta(seconds=pow(10, 9))
