import datetime


def add_gigasecond(original_date):
	DAYS_IN_GIGASECOND = (10 ** 9) / (24 * 60 * 60)
	return original_date + datetime.timedelta(DAYS_IN_GIGASECOND)
