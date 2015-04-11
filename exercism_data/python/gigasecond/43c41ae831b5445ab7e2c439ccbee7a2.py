import datetime;

def add_gigasecond(date):
	GIGA = 1000000000;

	final_date = date + datetime.timedelta(seconds=GIGA);

	return final_date
