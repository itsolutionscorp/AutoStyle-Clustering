import datetime

def add_gigasecond(birthday):
	if type(birthday) is datetime.date:
		return birthday + datetime.timedelta(seconds=(10**9))
	else: raise ValueError('add_gigasecond(birthday) expects birthday to be a datetime.date')
	

# # Gigasecond

# Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

# A gigasecond is one billion (10**9) seconds.
