from datetime import date
from calendar import Calendar

def meetup_day(Y, M, name_day, extra):
	days = {'Monday':0, 
		'Tuesday':1, 
		'Wednesday':2,
		'Thursday':3,
		'Friday':4,
		'Saturday':5,
		'Sunday':6, }
	month = Calendar().itermonthdays2(Y, M)
	days_in_month =[day for day in month if day[1] == days[name_day] and day[0] != 0]
	print(days_in_month)
	if extra == 'last':
		return date(Y,M,days_in_month[-1][0])
	elif extra == 'teenth':
		for day in days_in_month:
			if day[0]>= 13 and day[0]<=19:
				return date(Y,M,day[0])
	else:
		n = int(extra[0])-1
		return date(Y,M,days_in_month[n][0])
	
