# Find date of your 1 gigasecond aniversery

from datetime import date
import datetime

GIGASECOND = 10**9

def add_gigasecond(your_birthdate):
    """Return 1 gigasecond anniversery"""
    assert isinstance(your_birthdate, date)
    birthdate = datetime.datetime.fromordinal(your_birthdate.toordinal())
    gigasecond = birthdate + datetime.timedelta(seconds=GIGASECOND)
    return gigasecond.date()
