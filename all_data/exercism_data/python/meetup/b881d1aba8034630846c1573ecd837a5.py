import calendar
from datetime import date

calendar.setfirstweekday(6)

def meetup_day(year, month, dow, dom):
	
	month_list = calendar.monthcalendar(year, month)
	first_day = month_list[0].index(1)

	if dow == 'Sunday': dow_int = 0
	elif dow == 'Monday': dow_int = 1
	elif dow == 'Tuesday': dow_int = 2
	elif dow == 'Wednesday': dow_int = 3
	elif dow == 'Thursday': dow_int = 4
	elif dow == 'Friday': dow_int = 5
	else: dow_int = 6

	#get number of days until the 1st dow encountered & adj. if neg.
	daydiff = dow_int - first_day
	if (daydiff < 0): daydiff = daydiff + 7 

	#date of first dow encountered
	basedate = daydiff + 1

	if len(dom) == 3: #'1st', '2nd', '3rd', or '4th'
		week = int(dom[0]) - 1
		daydate = basedate + 7*week

	elif dom == 'teenth':
		if (basedate == 6): daydate = basedate + 7
		else: daydate = basedate + 14

	else: #dom == 'last'
 		totdays = calendar.monthrange(year, month)[1]
		daydate = basedate + 7*5
		if daydate > totdays: daydate = basedate + 7*4 

	return date(year, month, daydate)
