import calendar
import datetime

desc_commands = {
	'1st':0,
	'2nd':1,
	'3rd':2,
	'4th':3,
	'last':-1
}

def meetup_day(year, month, dayofweek, desc):
	teenth = [13,14,15,16,17,18,19]
	weekdays = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
	daysofmonth = calendar.monthcalendar(year,month)
	dates = []
	for week in daysofmonth:
		for day in week:
			if day != 0:
				dates.append(day)
	collect = []
	for day in dates:
		date = datetime.date(year, month, day)
		if dayofweek == weekdays[date.isoweekday()-1]:
			collect.append(date)
	if desc in desc_commands:
		index = desc_commands[desc]
		return collect[index]
	if desc == 'teenth':
		for day in collect:
			if day.day in teenth:
				return day
