from datetime import date, timedelta

def add_gigasecond(day):
	sInDay = 60*60*24
	sRemaining = 10**9
	while sRemaining > sInDay:
		day += timedelta(1)
		sRemaining -= sInDay
	return day
