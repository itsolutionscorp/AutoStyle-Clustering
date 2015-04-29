def add_gigasecond(startdate):
	from datetime import timedelta

	startdate = startdate + timedelta(seconds = 10**9)#11574)

	return startdate
