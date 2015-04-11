import datetime
def add_gigasecond(start_date):
	days_to_gigasecond = 10**9/24/3600
	end_date = start_date + datetime.timedelta(days=days_to_gigasecond)
	return end_date
