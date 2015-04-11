from datetime import date, timedelta

def meetup_day(year, month, day, spec):
	_date = date(year, month, 1)
	while _date.strftime("%A") != day:
		_date += timedelta(days=1)
		if _date.month != month:
			raise Exception("Cannot find the date")
	if spec[0].isdigit():
		weeks_offset = int(spec[0]) - 1
		_date += timedelta(days = 7 * weeks_offset)
	elif spec == "last":
		cur_month = _date.month
		while _date.month == cur_month:
			_date += timedelta(days = 7)
		_date -= timedelta(days = 7)
	elif spec == "teenth":
		while _date.day not in range(13,20):
			_date += timedelta(days = 7)
	else:
		raise Exception("Bad day number spec")
	return _date
