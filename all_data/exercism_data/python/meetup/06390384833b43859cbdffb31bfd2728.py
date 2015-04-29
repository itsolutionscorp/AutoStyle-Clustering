from datetime import date
from datetime import timedelta
from calendar import monthrange

DAYS_OF_WEEK = {
	'monday' : 0,
	'tuesday': 1,
	'wednesday': 2,
	'thursday' : 3,
	'friday' : 4,
	'saturday' : 5,
	'sunday' : 6 
}

def meetup_day(year, month, day_of_week, occurrence_in_month):
	meetup_date = None
	day_of_week = DAYS_OF_WEEK[day_of_week.lower()]
	if occurrence_in_month == '1st':
		meetup_date = get_first_of_month(month, year, day_of_week)
	elif occurrence_in_month == '2nd':
		meetup_date = get_second_of_month(month, year, day_of_week)
	elif occurrence_in_month == '3rd':
		meetup_date = get_third_of_month(month, year, day_of_week)
	elif occurrence_in_month == '4th':
		meetup_date = get_fourth_of_month(month, year, day_of_week)
	elif occurrence_in_month == 'last':
		meetup_date = get_last_of_month(month, year, day_of_week)
	elif occurrence_in_month == 'teenth':
		meetup_date = get_teenth_of_month(month, year, day_of_week)
	return meetup_date


def get_first_of_month(month, year, day_of_week):
	day = date(year, month, 1)
	while day.weekday() != day_of_week:
		day += timedelta(days=1)
	return day

def get_second_of_month(month, year, day_of_week):
	day = date(year, month, 7)
	while day.weekday() != day_of_week:
		day += timedelta(days=1)
	return day

def get_third_of_month(month, year, day_of_week):
	day = date(year, month, 14)
	while day.weekday() != day_of_week:
		day += timedelta(days=1)
	return day

def get_fourth_of_month(month, year, day_of_week):
	day = date(year, month, 21)
	while day.weekday() != day_of_week:
		day += timedelta(days=1)
	return day

def get_last_of_month(month, year, day_of_week):
	last_day_of_month = monthrange(year, month)[1]
	day = date(year, month, last_day_of_month)
	while day.weekday() != day_of_week:
		day -= timedelta(days=1)
	return day

def get_teenth_of_month(month, year, day_of_week):
	day = date(year, month, 13)
	while day.weekday() != day_of_week:
		day += timedelta(days=1)
	return day
