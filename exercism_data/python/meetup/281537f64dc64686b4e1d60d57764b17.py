import datetime as dt

import calendar

def teenth(yr,month,new):
	if new <= 5:
		return dt.date(yr,month,new+14)
	elif new == 6:
		return dt.date(yr,month,new+7)
	elif new == 7:
		return dt.date(yr,month,new+7)


def last(yr,month,new):
	if month != 2 and new <= 3:
		return dt.date(yr,month,new+28)
	elif month != 2 and new > 3:
		return dt.date(yr,month,new+21)
	elif month == 2 and yr%4 != 0:
		return dt.date(yr,month,new+21)
	elif month == 2 and yr%4 == 0:
		return dt.date(yr,month,new+28)


def meetup_day(yr,month,day,hint):
	new = 0
	for i in range(1,8):
		x = dt.date(yr,month,i)
		if calendar.day_name[x.weekday()] == day:
			new = i
			break
	if hint == '1st':
		return dt.date(yr,month,new)
	elif hint == '2nd':
		return dt.date(yr,month,new+7)
	elif hint == '3rd':
		return dt.date(yr,month,new+14)
	elif hint == '4th':
		return dt.date(yr,month,new+21)
	elif hint == '5th':
		return dt.date(yr,month,new+28)
	elif hint == 'teenth':
		return teenth(yr,month,new)
	elif hint == 'last':
		return last(yr,month,new)
