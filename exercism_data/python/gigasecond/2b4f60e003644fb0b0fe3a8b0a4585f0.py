from datetime import date as Date
from datetime import timedelta as Timedelta

def add_gigasecond(date):
    assert type(date) == type(Date.today()), "Input must be a valid date object."
    gigasecond = Timedelta(seconds=(10**9))
    return date + gigasecond
