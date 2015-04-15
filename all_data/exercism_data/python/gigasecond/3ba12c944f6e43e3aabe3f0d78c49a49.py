from datetime import date
from dateutil.relativedelta import relativedelta

	
from datetime import date
from dateutil.relativedelta import relativedelta
hello = date(1901,5,3)
def add_gigasecond(foo):
	giga = foo + relativedelta( seconds = 10**9 )
	giga = str(giga)
	giga = giga[0:10]
	year = int(giga[:4])
	month = int(giga[5:7])
	day = int(giga[-2:])
	return date(year, month, day)							 
print add_gigasecond(hello) 
	
	
