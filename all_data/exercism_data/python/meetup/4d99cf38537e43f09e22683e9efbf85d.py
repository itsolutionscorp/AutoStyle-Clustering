import datetime
import calendar

weekday_lut = {
	"Monday":	0,
	"Tuesday":	1,
	"Wednesday":2,
	"Thursday": 3,
	"Friday":	4,
	"Saturday":	5,
	"Sunday":	6
}

def get_weekdays_list(year, month, weekday_name):

	weekday = weekday_lut[weekday_name]
	day1, end_date = calendar.monthrange(year, month)

	#	find the 1st "weekday" of the month
	day = 1 + (weekday - day1) % 7

	dates = []
	while day <= end_date:
		dates.append(day)
		day += 7

	return dates

def meetup_day(year, month, weekday, which):

	dates = get_weekdays_list(year, month, weekday)

	#	get the nth date

	if which[0].isdigit():
		which = which[0]
		day = dates[int(which)-1]

	#	get the last date

	elif which == 'last':
		day = dates[-1]

	#	get the 'teenth' date

	else:
		for day in dates[1:3]:
			if day > 12 and day < 20:
				break

	return datetime.date(year, month, day)
