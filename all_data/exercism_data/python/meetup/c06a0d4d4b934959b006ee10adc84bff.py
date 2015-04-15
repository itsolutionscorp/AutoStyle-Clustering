import datetime

weekday_lut = {
	"Monday":	0,
	"Tuesday":	1,
	"Wednesday":2,
	"Thursday": 3,
	"Friday":	4,
	"Saturday":	5,
	"Sunday":	6
}

def get_weekdays_list(year, month, weekday):

	date = datetime.date(year, month, 1)
	delta = datetime.timedelta(days=1)
	weekday_index = weekday_lut[weekday]

	#	find the dates of all "weekday"s

	dates = []
	while True:
		if date.weekday() == weekday_index:
			dates.append(date.day)

		date += delta
		if date.month != month:
			break

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
