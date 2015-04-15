from calendar import monthrange
from datetime import date

def meetup_day(year, month, day_of_the_week, which):

	month_length = monthrange(year, month)[1]
	days_in_the_month = (date(year, month, day) for day in range(1, month_length+1))
	specific_day_candidates = [ day for day in days_in_the_month if day.strftime("%A") == day_of_the_week ]

	if which == 'teenth':
		return next(d for d in specific_day_candidates if 13 <= d.day <= 19)
	if which == 'last':
		return specific_day_candidates[-1]
	return specific_day_candidates[int(which[0])-1]
