from datetime import date
from datetime import timedelta

def add_gigasecond(x):

	x += timedelta(days=11574, seconds=6400)

	return x
