import calendar
from datetime import date

def meetup_day(year, month, weekday, which):
	month_calendar = calendar.Calendar().itermonthdates(year, month) # returns iterable [datetime.date(year, month, date)...]
	week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
	which_week = ['1st', '2nd', '3rd', '4th', 'last', 'teenth']
	correct_days_of_week = [day for day in month_calendar if day.month == month and day.weekday() == week.index(weekday)]
	'''if element in month_calendar has same .weekday() as the input weekend as determined by week.index(),
	put those datetime.date(year, month, date) into new list'''
	if which == 'teenth':
		return [i for i in correct_days_of_week if i.day in range(13,20)].pop() #pops the one and only element in the list
	elif which == 'last':
		return correct_days_of_week[-1]
	else:
		return correct_days_of_week[which_week.index(which)]
	
