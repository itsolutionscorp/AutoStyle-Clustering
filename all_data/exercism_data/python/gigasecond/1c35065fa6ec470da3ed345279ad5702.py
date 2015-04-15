from datetime import datetime, timedelta

def add_gigasecond(dtime):
	return dtime + timedelta(0, 1e9)
