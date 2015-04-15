from datetime import timedelta
def add_gigasecond(date1):
	gigasec = 10**9
	hours = gigasec / 3600
	tage = hours / 24
	return date1 + timedelta(days = tage)
