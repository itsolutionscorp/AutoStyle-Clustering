from datetime import datetime, timedelta, time

def add_gigasecond(date):
	return (datetime.combine(date, time()) + timedelta(seconds=1000000000)).date()
	
