from datetime import datetime, timedelta, date

def meetup_day(y, m, d, s):
	days = {"Monday": 1,
			"Tuesday": 2,
			"Wednesday": 3,
			"Thursday": 4,
			"Friday": 5,
			"Saturday": 6,
			"Sunday": 7}

	if s == "teenth":
		for i in range(10):
			new_date = date(year=y, month=m, day=10+i)
			if new_date.isoweekday() == days[d]:
				return new_date
	elif s == "last":
		for i in range(32, 1, -1):
			d_l = []
			try:
				new_date = date(year=y, month=m, day=i)
				if new_date.isoweekday() == days[d]:
					return new_date
			except ValueError:
				pass
			
	else:
		t = int(s[:1])
		d_l = []
		try:
			for i in range(1, 32):
				new_date = date(year=y, month=m, day=i)
				if new_date.isoweekday() == days[d]:
					d_l.append(new_date)
		except ValueError:
			pass
		return d_l[t-1]
