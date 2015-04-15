from datetime import date

def add_gigasecond(birthday):
	# Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.
	
	anniversary_days = 10**9/(60*60*24)

	anniversary = birthday.toordinal() + anniversary_days
	return date.fromordinal(anniversary)
