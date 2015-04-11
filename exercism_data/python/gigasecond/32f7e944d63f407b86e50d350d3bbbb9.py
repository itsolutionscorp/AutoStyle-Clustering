from datetime import date
from datetime import timedelta

def add_gigasecond(input_date):
    delta_t = timedelta(seconds=10**9)
    return input_date + delta_t
