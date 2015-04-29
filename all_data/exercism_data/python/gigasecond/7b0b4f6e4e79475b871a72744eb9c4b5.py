import datetime

def add_gigasecond(date_in):

	#add 1Gs using timedelta
	date_in = date_in + datetime.timedelta(seconds = 1000000000)

	return date_in
