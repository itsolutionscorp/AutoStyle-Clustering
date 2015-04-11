# meetup.py

from datetime import date, timedelta


def meetup_day(year, month, DOW, spec):
	
	
	if spec == 'last':
		if month != 12:
			start_day=date(year,month + 1,1)
		else:
			start_day=date(year+1,1,1)
		start_day = start_day - timedelta(days = 1)
		while True:
			if DOW == start_day.strftime('%A'):
				return start_day
			start_day = start_day - timedelta(days = 1)
			
	if spec == 'teenth':
		start_day=date(year,month,13)
		while True:
			if DOW == start_day.strftime('%A'):
				return start_day
			start_day = start_day + timedelta(days = 1)

	numeral = int(spec.strip('rdthsn'))
	start_day=date(year,month,1)
	count = 0
	while True:
			if DOW == start_day.strftime('%A'):
				count = count + 1
				if count == numeral:
					return start_day
			start_day = start_day + timedelta(days = 1)
