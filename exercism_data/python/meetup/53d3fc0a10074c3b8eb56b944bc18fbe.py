from calendar import Calendar
import datetime

def meetup_day(year, month, day_of_week, suffix):
	cal = Calendar(1)
	dayList = list(cal.itermonthdates(year, month))

	# Translate human numbers to real numbers
	dateDic = {
		'1st': 0,
		'2nd': 1,
		'3rd': 2,
		'4th': 3,
		'last': -1,
	}

	# Translate human days to numeric days
	dayDic = {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6,
	}
	# Our list of suspects
	possibleDays = []

	# Treat teenth specially(does anyone actually say that?)
	# I don't like this, it looks all ugly :(
	if suffix == 'teenth':
		for day in dayList:
			if day.day in range(13, 19) and \
			day.weekday() == dayDic[day_of_week]:
				meetupDay = day
	else:
		for day in dayList:
			# If it's the right day of the week and the right month, it's a suspect
			if day.weekday() == dayDic[day_of_week] and \
			day.month == month:
				possibleDays.append(day)
		meetupDay = possibleDays[dateDic[suffix]]
		
	return meetupDay
