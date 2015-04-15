import time, datetime, calendar

DAYS = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
MODS = {'teenth': [13, 14, 15, 16, 17, 18, 19], 'last': 5, '1st': 0, '2nd': 1, '3rd': 2, '4th': 3 }

def meetup_day(year, month, day, mod):
	days = []
	for week in calendar.monthcalendar(year, month):
		if week[DAYS[day]] != 0:
			days.append(week[DAYS[day]])
		
	if mod == 'teenth':
		for date in days:
			if date in range(13, 20):
				return datetime.date(year, month, date)
	elif mod == 'last':
		return datetime.date(year, month, days[-1])
	else:
		return datetime.date(year, month, days[MODS[mod]])
