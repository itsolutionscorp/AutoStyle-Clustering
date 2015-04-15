import datetime
from datetime import date
from year import is_leap_year

def add_gigasecond(datum):
    return datum + datetime.timedelta(0,1e9)
