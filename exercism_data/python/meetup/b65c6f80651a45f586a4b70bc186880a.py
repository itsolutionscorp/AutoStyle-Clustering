from datetime import date
from calendar import monthrange

def meetup_day(year, month, day, which_one):
	days = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
	weeks = {'1st':7, '2nd':14, '3rd':21, '4th':28, 'teenth':19}
	weeks['last'] = monthrange(year, month)[1]
	
	new_date = date(year, month, 1)
	i = weeks[which_one]
	which_weekday = days[day]
	for x in range(i-6, i+1):
		temp = new_date.replace(day=x)
		if temp.weekday() == which_weekday:
			return temp
