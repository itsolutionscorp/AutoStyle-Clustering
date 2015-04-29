from datetime import date, timedelta

days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

def find_instances_of_weekday(year, month, day_name):
	d = date(year, month, 1)
	while d.month == month:
		if days_of_week[d.weekday()] == day_name:
			yield d
		d += timedelta(days=1)

def meetup_day(year, month, day_name, recurrence):
	candidates = list(find_instances_of_weekday(year, month, day_name))
	if recurrence == 'first':
		n = 0
	elif recurrence == 'last':
		n = -1
	elif recurrence == 'teenth':
		n = [i for i in xrange(len(candidates)) if 13 <= candidates[i].day <= 19][0]
	else:
		n = int(recurrence[:-2]) - 1
	return candidates[n]
