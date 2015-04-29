from datetime import date
from calendar import monthrange

def meetup_day(year,month,day_of_week,which_day):
	num_days_in_month = monthrange(year,month)[1]
	days_in_month = (date(year,month,day) for day in range(1, num_days_in_month + 1))\

	possible_days = [d for d in days_in_month if get_day(d) == day_of_week]

	if which_day == 'teenth':
		return next(d for d in possible_days if 13 <= d.day <= 19)
	elif which_day == 'last':
		return possible_days[-1]
	else:
		return possible_days[int(which_day[0])-1]


def get_day(date):
	return date.strftime('%A')
