from calendar import timegm
from time import gmtime, strftime, strptime
from datetime import date, timedelta
def add_gigasecond(start_date):
    giga = 10**9
    increment = timedelta(seconds=giga)
    anniversary = start_date + increment
    return anniversary
