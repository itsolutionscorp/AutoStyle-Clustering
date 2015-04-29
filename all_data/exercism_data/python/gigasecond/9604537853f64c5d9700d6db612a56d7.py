import datetime
from datetime import date
#date(2011, 4, 25)

def add_gigasecond(date1):
    #n = 11574
    n = 1000000000
    d = date1 + datetime.timedelta(seconds=n)
    return d

