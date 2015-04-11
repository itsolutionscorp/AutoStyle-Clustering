from datetime import date
from dateutil.relativedelta import relativedelta

def add_gigasecond(foo):
	giga = foo + relativedelta( seconds = 10**9 )
	giga = str(giga)
	year = int(giga[:4])
	month = int(giga[5:7])
	day = int(giga[-2:])
	return date(year, month, day)							 

	
	
