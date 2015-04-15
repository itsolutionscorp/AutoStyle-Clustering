import datetime 

def add_gigasecond(init_date):
	return init_date + datetime.timedelta(seconds=pow(10,9))
