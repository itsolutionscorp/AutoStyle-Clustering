import datetime as dt

def meetup_day(yr,month,day,hint):
	if day == 'Monday':
		first = 0
	elif day == 'Tuesday':
		first = 1
	elif day == 'Wednesday':
		first = 2
	elif day == 'Thursday':
		first = 3
	elif day == 'Friday':
		first = 4
	elif day == 'Saturday':
		first = 5
	else:
		first = 6
	for i in range(1,8):
		x = dt.date(yr,month,i)
		if x.weekday() == first:
			new = i
			break
	if hint == '1st':
		return dt.date(yr,month,new)
	elif hint == '2nd':
		return dt.date(yr,month,new+7)
	elif hint == 'teenth' and new <= 5:
		return dt.date(yr,month,new+14)
	elif hint == 'teenth' and new == 6:
		return dt.date(yr,month,new+7)
	elif hint == 'teenth' and new == 7:
		return dt.date(yr,month,new+7)
	elif hint == '3rd':
		return dt.date(yr,month,new+14)
	elif hint == '4th':
		return dt.date(yr,month,new+21)
	elif hint == '5th':
		return dt.date(yr,month,new+28)
	elif hint == 'last' and month != 2 and new <=3:
		return dt.date(yr,month,new+28)
	elif hint == 'last' and month != 2 and new > 3:
		return dt.date(yr,month,new+21)
	elif hint == 'last' and month == 2 and yr%4 != 0:
		return dt.date(yr,month,new+21)
	elif hint == 'last' and month == 2 and yr%4 == 0:
		return dt.date(yr,month,new+28)
