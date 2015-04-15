from datetime import date
from datetime import timedelta
def meetup_day(year, month, dat, mod):
	weekday=date(year,month,1).weekday()
	lastdate=date(year,(month+1)%12,1)-timedelta(1)
	lastweekday=lastdate.weekday()
	teen=date(year, month, 13).weekday()
	if(dat=='Monday'):
		day=0
	if(dat=='Tuesday'):
		day=1
	if(dat=='Wednesday'):
		day=2
	if(dat=='Thursday'):
		day=3
	if(dat=='Friday'):
		day=4
	if(dat=='Saturday'):
		day=5
	if(dat=='Sunday'):
		day=6
	diff=day-weekday
	diff2=day-lastweekday
	diff3=day-teen
	if(mod=='teenth'):
		if(diff3>=0):
			return date(year,month, 13) + timedelta(diff3)
		if(diff3<0):
			return date(year, month, 13) +timedelta(diff3+7)
	if(mod=='last'):
		return lastdate+timedelta(diff2)
	if(diff>=0):
		if(mod=='1st'):
			return date(year, month, 1+diff)
		if(mod=='2nd'):
			return date(year, month,1+diff+7)
		if(mod=='3rd'):
			return date(year, month, 1+diff+14)
		if(mod=='4th'):
			return date(year, month, 1+diff+21)
		if(mod=='teenth'):
			return date(year, month, 1)
	if(diff<0):
		if(mod=='1st'):
			return date(year, month, 1+diff+7)
		if(mod=='2nd'):
			return date(year, month, 1+diff+14)
		if(mod=='3rd'):
			return date(year, month, 1+diff+21)
		if(mod=='4th'):
			return date(year, month, 1+diff+28)
