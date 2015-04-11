from datetime import date
from datetime import timedelta


def add_gigasecond(date):
    td = timedelta(seconds=1000000000)
    return(date + td)
