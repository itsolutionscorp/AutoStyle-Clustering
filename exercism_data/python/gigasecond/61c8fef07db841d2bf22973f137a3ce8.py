''' gigasecond.py 
	created 25 Sept 2014
	by @jestuber
	'''
from datetime import timedelta

def add_gigasecond(datein):
	return datein + datetime.timedelta(seconds=10**9)
