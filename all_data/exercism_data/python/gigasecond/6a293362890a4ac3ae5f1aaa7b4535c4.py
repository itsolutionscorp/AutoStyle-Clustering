from datetime import date, timedelta



def add_gigasecond(start_date):
	gigaseconds = timedelta(seconds=10**9)
	return start_date + gigaseconds
