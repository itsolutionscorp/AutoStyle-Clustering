from calendar import monthrange, day_name
from datetime import date

def meetup_day(year, month, weekday, occurence):
	days_in_month = monthrange(year, month)[1]

	weekdays = {
		"Monday": 0,
		"Tuesday": 1,
		"Wednesday": 2,
		"Thursday": 3,
		"Friday": 4,
		"Saturday": 5,
		"Sunday": 6
	}

	days = []

	for day in range(1, days_in_month + 1):
		if date(year, month, day).weekday() == weekdays[weekday]:
			days.append(date(year, month, day))

	occurences = {
		"teenth": [13, 14, 15, 16, 17, 18, 19],
		"1st": 0,
		"2nd": 1,
		"3rd": 2,
		"4th": 3,
		"last": -1
	}

	if occurence == "teenth":
		for day in days:
			if day.day in occurences["teenth"]:
				return day
	else: 
		return days[occurences[occurence]]

print meetup_day(2015, 1, 'Tuesday', 'teenth')
