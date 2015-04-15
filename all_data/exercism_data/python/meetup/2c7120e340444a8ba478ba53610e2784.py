from datetime import date, timedelta

def meetup_day(y,m,wd,ordin):
	if ordin == 'teenth':
		day = 13
		while not date(y,m,day).weekday() is weekdays[wd]:
			day += 1
	elif ordin == 'last':
		day = (date(y,m+1,1)+timedelta(-1)).day
		while not date(y,m,day).weekday() is weekdays[wd]:
			day -= 1
	else:
		count = 0
		day = 0
		while ordins[ordin] != count:
			day += 1
			if date(y,m,day).weekday() is weekdays[wd]:
				count += 1
	return date(y,m,day)

weekdays = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
ordins = {'1st':1,'2nd':2,'3rd':3,'4th':4}
