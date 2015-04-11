from datetime import date, timedelta

def add_gigasecond(date):
	answer = date + timedelta(seconds=10**9)
	return answer
