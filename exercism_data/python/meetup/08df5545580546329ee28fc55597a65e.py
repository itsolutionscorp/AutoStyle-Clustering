from datetime import *

weekday_table = {
	"Monday": 1,
	"Tuesday": 2,
	"Wednesday": 3,
	"Thursday": 4,
	"Friday": 5,
	"Saturday": 6,
	"Sunday": 7
}

def meetup_day(year, month, weekday, specifier):
	day = date(year, month, 1)

	while day.isoweekday() != weekday_table[weekday]:
		day += day.resolution

	if specifier == "1st" or specifier == "first":
		return day
	elif specifier == "2nd":
		return day + timedelta(7)
	elif specifier == "3rd":
		return day + timedelta(14)
	elif specifier == "4th":
		return day + timedelta(21)
	elif specifier == "last":
		day += timedelta(21)
		last = day + timedelta(7)
		if last.month == month:
			return last
		else:
			return day
	elif specifier == "teenth":
		teenth = day + timedelta(7)
		if teenth.day > 12:
			return teenth
		else:
			return teenth + timedelta(7)
	else:
		return day.max

