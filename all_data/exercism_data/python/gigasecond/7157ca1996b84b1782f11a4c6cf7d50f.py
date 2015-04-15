import datetime

def add_gigasecond(date):

	gigasecond= datetime.timedelta(0,10**9)

	gig_date = date + gigasecond
	return gig_date
