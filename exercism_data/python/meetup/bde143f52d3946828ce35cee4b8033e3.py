from datetime import date
from dateutil.relativedelta import *
import calendar

def meetup_day(year, month, day, pattern):
	# cleanup
	day = day.title()
	pattern = pattern.lower()

	# lookup for relative delta
	whichDay = {'Monday': MO, 'Tuesday': TU, 'Wednesday': WE,'Thursday': TH, 'Friday': FR, 'Saturday': SA,'Sunday': SU }
	whichPattern = { '1st': 1, '2nd': 2 , '3rd': 3, '4th': 4, 'last': -1 , 'teenth':1}

	if(pattern =='teenth'):
		offset = 13
		startDate = date(year, month, 13)
	else:	
		offset = 31 if (pattern == 'last') else 1 			
		startDate = date(year, month, 1) 
	
	return startDate + relativedelta(day=offset, weekday=whichDay[day](whichPattern[pattern])) 	
	
