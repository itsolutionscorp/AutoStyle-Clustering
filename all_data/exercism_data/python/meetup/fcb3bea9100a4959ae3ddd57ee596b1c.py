from datetime import date
from dateutil.relativedelta import *
import calendar

def meetup_day(year, month, day, pattern):
	day = day.title()
	pattern = pattern.lower()

	# lookup
	whichDay = {
		'Monday': 0, 
		'Tuesday': 1, 
		'Wednesday': 2,
		'Thursday': 3, 
		'Friday': 4, 
		'Saturday': 5,
		'Sunday': 6 
	}
	whichPattern = {
		'1st': 0, '2nd': 1 , '3rd': 2, '4th': 3	
	}

	monthRange = calendar.monthrange(year, month)
	

	if pattern == 'last':
		lastWeek = [(x-28+monthRange[0]) % 7 for x in range(monthRange[1]-7, monthRange[1]+1)]
		return date(year, month, monthRange[1]-6+lastWeek.index(whichDay[day]))
	elif pattern == 'teenth':
		teenthWeek = [(x-15+monthRange[0]) % 7 for x in range(13,20)]
		return date(year, month, 13+teenthWeek.index(whichDay[day]))
	else:	
		dayDelta = whichDay[day] - monthRange[0]
		weekSelector = whichPattern[pattern]
		# handles sequential patterns: e.g: 1st, 2nd, 3rd, 4th	
		offset = (7*weekSelector)+(7- abs(dayDelta)) if dayDelta < 0 else dayDelta + (7*weekSelector)
		return date(year, month, 1+offset)

def meetup_day_alternate(year, month, day, pattern):
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

	
