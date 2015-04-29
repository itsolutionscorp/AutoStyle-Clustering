# gigasecond.py

import datetime

def add_gigasecond(birthday):
    """
    Assumes birthday is instance of 'date'.
    Takes person's sbirthday 'date(Year, Month, Day)' and returns
    'Year-Month-Day' of the billionth second of person's existence.
    """

    return ( birthday + (datetime.timedelta(seconds = (10**9))) )
