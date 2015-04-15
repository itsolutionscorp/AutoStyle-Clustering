import calendar
import datetime

WEEKDICT = { 
	'1st': range(1, 8),
	'2nd': range(8, 15),
	'3rd': range(15, 22),
	'4th': range(22, 29),
	'last': range(-6, 1),
	'teenth': range(13, 20),
}

def meetup_day(year, month, weekday, week):
	chosen_week = WEEKDICT[week]
	daysinmonth = calendar.monthrange(year, month)[1]
	if chosen_week[0] < 0:
		chosen_week = [x + daysinmonth for x in chosen_week]
	for day in chosen_week:
		date = datetime.date(year, month, day)
		if date.strftime('%A') == weekday:
			return date
