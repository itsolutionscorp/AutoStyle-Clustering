from datetime import date
import calendar
from string import *

def meetup_day(y,m,d,pos):
	mw=[]
	w=calendar.day_name
	for a in w:
		mw.append(a)
	dowi=mw.index(d)
	l=calendar.monthcalendar(y,m)
	if (pos=='teenth'):
		i=0
		while (l[i][dowi] < 13):
			i+=1
		rday=l[i][dowi]
	elif (pos=='last'):
		rday=l[-1][dowi]
		if (rday==0):
			rday=l[-2][dowi]
	else:
		day=l[0][dowi]
		if (day==0):
			day=l[1][dowi]
		if (pos=='1st'):
			rday=day
		elif (pos=='2nd'):
			day+=7
			rday=day			
		elif (pos=='3rd'):
			day+=14
			rday=day
		else:
			day+=21			
			rday=day				
	
	return date(y, m, rday)
