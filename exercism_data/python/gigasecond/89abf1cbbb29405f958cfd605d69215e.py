from datetime import timedelta

def add_gigasecond(birth_date):
	giga_second_date = birth_date + timedelta(seconds=1000000000)
	return giga_second_date
