from datetime import date

def add_gigasecond(target):
	seconds = 0
	year = target.year
	month = target.month
	day = target.day
	s_in_year = 31556952
	s_in_month = 2630000
	s_in_day = 86400
	while seconds + s_in_year < 10**9:
		seconds += s_in_year
		year += 1
	while seconds + s_in_month < 10**9:
		if month > 12:
			month = 1
			day = 1
		else:
			month += 1
		seconds += s_in_month
	while seconds + s_in_day < 10**9:
		if day > 31:
			day = 1
			if month == 12:
				month = 1
				year +=1
		else:
			day += 1
		seconds += s_in_day

	return date(year, month, day)
		

