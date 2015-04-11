#/usr/bin/env python
from datetime import date
from calendar import monthrange
def meetup_day(year,month,day,aa):
	rday = int(day.replace('Monday','1').replace('Tuesday','2').replace('Wednesday','3').replace('Thursday','4').replace('Friday','5').replace('Saturday','6').replace('Sunday','7'))
	lliist = []
	for xx in range(1,monthrange(year,month)[1]+1):
		if date(year,month,xx).isoweekday() == rday:
			lliist.append(date(year,month,xx))
	if aa == '1st':
		return lliist[0]
	elif aa == '2nd':
		return lliist[1]
	elif aa == '3rd':
		return lliist[2]
	elif aa == '4th':
		return lliist[3]
	elif aa == 'teenth':
		for nnm in lliist:
			if nnm.day >= 13 and nnm.day <= 19:
				return nnm
	elif aa == 'last':
		return lliist[len(lliist)-1]

print meetup_day(2015,2,'Saturday','1st')
