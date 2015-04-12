import calendar
from datetime import date

def meetup_day(year, month, day, hint):
	cal = calendar.Calendar(0)
	
	day_dict = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4, "Saturday" : 5, "Sunday" : 6}
	teenth_list = [13, 14, 15, 16, 17, 18, 19]
	
	#Retrieve numbers for specific day throughout entire month
	days = []
	for week in cal.monthdayscalendar(year, month):
		days.append(week[day_dict[day]])
	
	#remove all occurrences of days not in month, indicated by a 0
	days = [i for i in days if i!=0 ]
	#print days

	if hint is '1st':
		return date(year, month, days[0])
	elif hint is '2nd':
		return date(year, month, days[1])
	elif hint is '3rd':
		return date(year, month, days[2])
	elif hint is '4th':
		return date(year, month, days[3])
	elif hint is 'last':
		return date(year, month, days[len(days) - 1])
	else:
		days = [day for day in days if day >= 13 and day <= 19]
		return date(year, month, days[0])

#print meetup_day(1995, 1, 'Sunday', 'teenth')