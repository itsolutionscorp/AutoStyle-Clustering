from datetime import date
import calendar

def meetup_day(year, month, day_str, itir_in_month):
	c = calendar.monthcalendar(year, month)
	week_dict = {"Monday" : 0, "Tuesday" : 1, "Wednesday" : 2, "Thursday" : 3, "Friday" : 4, "Saturday" : 5, "Sunday" : 6}

	if itir_in_month == "teenth":
		for week in c:
			if 12 < week[week_dict[day_str]] < 20:
				return date(year, month, week[week_dict[day_str]])
	elif itir_in_month == "last":
		if c[-1][week_dict[day_str]] != 0:
			return date(year, month, c[-1][week_dict[day_str]])
		else:
			return date(year, month, c[-2][week_dict[day_str]])
	else:
		if c[0][week_dict[day_str]] != 0:
			return date(year, month, c[int(itir_in_month[0])-1][week_dict[day_str]])
		else:
			return date(year, month, c[int(itir_in_month[0])][week_dict[day_str]])
