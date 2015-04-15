from datetime import datetime
from datetime import date
from datetime import timedelta

def add_gigasecond(theday):
	converted_date = datetime.combine(theday, datetime.min.time())  
	giga_date_time = converted_date + timedelta(days=11574)
	return giga_date_time.date()
