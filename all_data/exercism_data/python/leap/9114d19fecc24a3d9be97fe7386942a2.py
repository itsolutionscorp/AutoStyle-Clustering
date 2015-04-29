def is_leap_year(year):
	try:
		isInstance(year, int)
	except Exception, e:
		print 'Not an integer.'
	return  not year % 4 and year % 100 or not year % 400  
