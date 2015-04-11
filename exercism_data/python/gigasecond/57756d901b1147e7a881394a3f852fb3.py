#/usr/bin/env python
from datetime import date, timedelta

def add_gigasecond(birth):
	return birth + timedelta(seconds=10**9)
