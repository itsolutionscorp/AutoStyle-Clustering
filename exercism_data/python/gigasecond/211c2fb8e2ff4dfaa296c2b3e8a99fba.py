from datetime import date, timedelta

def add_gigasecond(day):
	sInDay = timedelta(1).total_seconds()
	sRemaining = 10**9
	while sRemaining > sInDay:
		day += timedelta(1)
		sRemaining -= sInDay
	return day
