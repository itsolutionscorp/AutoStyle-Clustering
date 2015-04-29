def meetup_day(year, month, weekday, which_one):
	select_nums = {
		'1st': '1',
		'2nd': '2',
		'3rd': '3',
		'4th': '4',
		'5th': '5'
	}
	if which_one in select_nums:
		which_one = select_nums[which_one]

	from calendar import monthrange
	from datetime import date
	day_dates = {}
	dates_count = 1
	teenth_occured = False

	weekday = day_to_num(weekday)
	month_days = monthrange(year, month)
	for day in range(1, month_days[1] + 1):
		if date(year, month, day).weekday() == weekday:
			if not teenth_occured and day > 10:
				day_dates['teenth'] = day
				teenth_occured = True

			elif day >= month_days[1] - 7:
				day_dates['last'] = day

			day_dates[str(dates_count)] = day
			dates_count += 1

	return date(year, month, day_dates[which_one])

def day_to_num(weekday):
	return {
		'Monday': 0,
		'Tuesday': 1,
		'Wednesday': 2,
		'Thursday': 3,
		'Friday': 4,
		'Saturday': 5,
		'Sunday': 6
	}[weekday]
