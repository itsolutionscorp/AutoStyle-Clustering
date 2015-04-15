from datetime import date
from calendar import monthrange

def meetup_day(year, month, dow, occurrence):
	dow_nums = {'Monday' : 0, 'Tuesday' : 1, 'Wednesday' : 2, 'Thursday' : 3, 'Friday' : 4, 'Saturday' : 5, 'Sunday' : 6}
	days = [x for x in range(1, monthrange(year,month)[1]+1) if date(year, month, x).weekday() == dow_nums[dow]]
	
	if occurrence == 'teenth':		
		days = filter(lambda x: x >= 13 and x < 20, days)
		return date(year, month, days[0])
	elif occurrence == 'last':
		return date(year, month, days[-1])
	else:
		position = int(occurrence[0]) - 1
		return date(year, month, days[position])
