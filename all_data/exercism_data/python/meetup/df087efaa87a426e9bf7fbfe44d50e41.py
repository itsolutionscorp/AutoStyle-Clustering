from datetime import date, timedelta
from calendar import monthrange

DAYS = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']

def meetup_day(year,month,day,which):
	dayindex = DAYS.index(day)
	firstday = date(year,month,1)
	lastdate = monthrange(year,month)[1]
	if(firstday.weekday()-dayindex > 0):
		d = 7 - firstday.weekday()-dayindex
	else:
		d = dayindex-firstday.weekday()
	if which == '1st':
		return firstday + timedelta(days=d)
	elif which == '2nd':
		return firstday + timedelta(days=d+7)
	elif which == '3rd':
		return firstday + timedelta(days=d+14)
	elif which == '4th':
		return firstday + timedelta(days=d+21)
	elif which == 'teenth':
		thirteenth = date(year,month,13)
		if(thirteenth.weekday() - dayindex > 0 ):
			return thirteenth + timedelta(days=7 - thirteenth.weekday()-dayindex)
		else:
			return thirteenth + timedelta(days=dayindex-thirteenth.weekday())
	elif which == 'last':
		for i in range(lastdate-6,lastdate+1):
			d = date(year,month,i)
			if(d.weekday() == dayindex):
				return d
	else:
		return

	


print meetup_day(2014,5,'Wednesday','last')
