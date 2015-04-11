from datetime import date
import calendar

def meetup_day(year, month, weekday, modifier):
	weekdays_of_month = map_month(year,month)
	week = interpret_modifier(modifier)
	target_day = weekday_to_num(weekday)
	if type(week) == int:
		day = weekdays_of_month.index(target_day,(week-1)*7,week*7)+1
	elif week == "teenth":
		day = weekdays_of_month.index(target_day,12,19)+1
	elif week == "last":
		day = weekdays_of_month[-7:].index(target_day)+len(weekdays_of_month)-6
	finaldate = date(year,month,day)
	return finaldate


def weekday_to_num(weekday):
	week = ["Monday", "Tuesday",
					"Wednesday", "Thursday",
					"Friday", "Saturday",
					"Sunday"]
	return week.index(weekday)
	
def interpret_modifier(mod):
	if mod == '1st':
		return 1
	elif mod == '2nd':
		return 2
	elif mod == '3rd':
		return 3
	elif mod == '4th':
		return 4
	return mod


def map_month(year, month):
	cal = calendar.Calendar()
	days_of_month = cal.itermonthdates(year,month)
	weekdays = []
	for day in days_of_month:
		if day.month == month:
			weekdays.append(day.weekday())
	return weekdays
