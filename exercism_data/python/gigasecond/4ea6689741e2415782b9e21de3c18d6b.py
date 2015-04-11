import datetime
def add_gigasecond(start_time):
	ret_time = start_time + datetime.timedelta(0, 10**9)
	return ret_time
