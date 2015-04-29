from datetime import timedelta
from datetime import date

###################################
# Function adds a giga-second to a given date
# inputs: date
# returns: 	date
def add_gigasecond(start_date):
	giga_sec = 10**9
	end_date= start_date + timedelta(seconds=giga_sec)
	
	return end_date
