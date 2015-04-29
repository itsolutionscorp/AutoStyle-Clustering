import datetime 

def add_gigasecond(orig_time):
	new_time = orig_time + datetime.timedelta(0, 10**9)
	print new_time
	return new_time
	
	
