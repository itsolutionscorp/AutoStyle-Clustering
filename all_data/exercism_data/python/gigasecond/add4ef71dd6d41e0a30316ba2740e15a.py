"""# Gigasecond

Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

A gigasecond is one billion (10**9) seconds.
"""

def add_gigasecond(date):
	import datetime
	from datetime import timedelta
	nday = date + timedelta(seconds = 10**9)
	return nday
