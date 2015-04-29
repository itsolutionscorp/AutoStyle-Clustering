from dateutil.relativedelta import relativedelta
from datetime import date

def add_gigasecond(bdate):
	ft_date =  bdate + relativedelta(seconds= 10 ** 9)
	return date(ft_date.year,ft_date.month,ft_date.day)
