from datetime import date, timedelta

def meetup_day(year, month, weekday, which_day):
	meetup = date(year, month, 1)
	next_week = meetup + timedelta(days=7)
	teenth = [x for x in range(13,20)]
	count = 0
	while True:
		if meetup.strftime("%A") == weekday:
			count +=1
			if str(count) == which_day[0] or (meetup.day in teenth and which_day == 'teenth'):
				return meetup
			if which_day == 'last' and next_week.month != meetup.month:
				return meetup
		meetup += timedelta(days=1)
		next_week += timedelta(days=1)
	
