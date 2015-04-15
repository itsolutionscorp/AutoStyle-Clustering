import calendar 
from datetime import date

dayOfWeek = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}
teenthDays = [13, 14, 15, 16, 17, 18, 19]

def meetup_day(y, m, d, iteration):
	dateOfWeek = dayOfWeek[d]

	if iteration == "1st":
		try: 
			return date(y, m, (calendar.monthcalendar(y, m)[0][dateOfWeek]))
		except ValueError:
			return date(y, m, (calendar.monthcalendar(y, m)[1][dateOfWeek]))

	elif iteration == "2nd":
		if calendar.monthcalendar(y, m)[0][dateOfWeek]:
			return date(y, m, (calendar.monthcalendar(y, m)[1][dateOfWeek]))
		else:
			return date(y, m, (calendar.monthcalendar(y, m)[2][dateOfWeek]))

	elif iteration == "3rd":
		if calendar.monthcalendar(y, m)[0][dateOfWeek]:
			return date(y, m, (calendar.monthcalendar(y, m)[2][dateOfWeek]))
		else:
			return date(y, m, (calendar.monthcalendar(y, m)[3][dateOfWeek]))

	elif iteration == "4th":
		if calendar.monthcalendar(y, m)[0][dateOfWeek]:
			return date(y, m, (calendar.monthcalendar(y, m)[3][dateOfWeek]))
		else:
			return date(y, m, (calendar.monthcalendar(y, m)[4][dateOfWeek]))

	elif iteration == "last":
		return date(y, m, (calendar.monthcalendar(y, m)[-1][dateOfWeek]))

	elif iteration == "teenth":
		for i in calendar.monthcalendar(y, m):
			if i[dateOfWeek] in teenthDays:
				return date(y, m, i[dateOfWeek])
