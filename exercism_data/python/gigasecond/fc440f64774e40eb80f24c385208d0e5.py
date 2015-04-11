from datetime import date
from datetime import timedelta

gigasecond = (10**9)

def add_gigasecond(date):
    return date+timedelta(days=days_in_gs())

def days_in_gs():
    days = (((gigasecond/60)/60)/24)
    return days
