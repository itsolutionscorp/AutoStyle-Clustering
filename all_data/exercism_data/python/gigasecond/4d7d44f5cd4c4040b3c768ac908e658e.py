'''
giga second exercise
'''

def add_gigasecond(date):
	from datetime import timedelta
	return date + timedelta(seconds = 10**9)
