from datetime import datetime, date;
import calendar;

def get_day(year, month, day_of_week, mod):
	week = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3,
					"Friday": 4, "Saturday": 5, "Sunday": 6};
	count = 0;

	if mod == 5:
		new_range = range(calendar.monthrange(year, month)[1], 0, -1);
		stop = 0;
	elif mod == 6:
		new_range = range(13, 20);
		stop = 0;
	else:
		new_range = range(1, calendar.monthrange(year, month)[1] + 1);
		stop = mod;

	for i in new_range:
		if date(year, month, i).weekday() == week[day_of_week]:
			if count == stop:
				return i;
			else:
				count += 1;

	raise ValueError("That combination doesn't exist.");

def meetup_day(year, month, day_of_week, mod):
	# Days that end in 'teenth': 13, 14, 15, 16, 17, 18, 19

	mod_limit = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3, "5th": 4,
							 "last": 5, "teenth": 6};

	return date(year, month, get_day(year, month, day_of_week, mod_limit[mod]));
