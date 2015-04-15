from datetime import date,timedelta


def meetup_day(year,month,dayofweek,dayinmonth):
	FDM = date(year,month,1)
	if dayofweek == "Monday":
		iday = 1
	elif dayofweek == "Tuesday":
		iday = 2
	elif dayofweek == "Wednesday":
		iday = 3
	elif dayofweek == "Thursday":
		iday = 4
	elif dayofweek == "Friday":
		iday = 5#
	elif dayofweek == "Saturday":
		iday = 6
	elif dayofweek == "Sunday":
		iday = 7

	iweek = -1

	if dayinmonth == "1st":
		iweek = 0#
	elif dayinmonth == "2nd":
		iweek = 7
	elif dayinmonth == "3rd":
		iweek = 14
	elif dayinmonth == "4th":
		iweek = 21
	elif dayinmonth == "last":
		for x in xrange(14):
			try:
				if(date(year,month,31-x).isoweekday() == iday):
					day = 31-x
					break
			except:
				pass
	elif dayinmonth == "teenth":
		for x in xrange(7):
			if date(year,month,13+x).isoweekday() == iday:
				day = 13+x
				break
		


	if not iweek == -1:
		day = (iday - FDM.isoweekday())%7+1 + iweek

	return date(year,month,day)
