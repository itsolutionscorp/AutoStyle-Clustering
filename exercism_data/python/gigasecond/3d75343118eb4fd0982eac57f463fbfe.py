from datetime import date
from year import is_leap_year

nyear = {days[0]:days[1] for days in zip(range(1,13),[31,28,31,30,31,30,31,31,30,31,30,31])}
lyear = nyear
lyear[2] = 29
gig2day = int(1e9/(24*3600))

def add_gigasecond(dtobj):
	day0 = 0
	y = dtobj.year
	m = dtobj.month
	day = dtobj.day
	while (day0-day)<gig2day:
		if is_leap_year(y):
			year = lyear
		else:
			year = nyear
		for month in range(m,13):
			if day0 + year[month] >= gig2day:
				return date(y,month,gig2day-day0)
			day0 += year[month]
			if month >= 12:
				m = 1
		y += 1
