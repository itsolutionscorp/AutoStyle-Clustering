from datetime import timedelta

def add_gigasecond(input_date):
	return input_date + timedelta(seconds = 10**9) 
