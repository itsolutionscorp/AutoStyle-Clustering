''' gigasecond.py 
	created 25 Sept 2014
	by @jestuber
	'''
import datetime

def add_gigasecond(datein):
	return datein + datetime.timedelta(seconds=1e9)
