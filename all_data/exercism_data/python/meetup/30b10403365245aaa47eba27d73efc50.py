from datetime import date
import calendar

def meetup_day(year, month, dayOfWeek, dayInMonth):
	cal = calendar.monthcalendar(year, month)
	weekdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
	day = weekdays[dayOfWeek]
	teens = list(range(13, 20))

	if dayInMonth == 'teenth':
		for week in cal:
			if week[day] in teens:
				return date(year, month, week[day])
	elif dayInMonth == 'last':
		for week in reversed(cal):
			if week[day] != 0:
				return date(year, month, week[day])
	else:
		count = 0
		for week in cal:
			if week[day] != 0:
				count+=1
			if count == int(dayInMonth[0]):
				return date(year, month, week[day])
			elif count > int(dayInMonth[0]) or week == cal[-1]:
				raise Exception('Day not in month.')
