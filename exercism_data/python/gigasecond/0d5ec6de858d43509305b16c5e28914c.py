from datetime import datetime, timedelta

def add_gigasecond(date):
    if not isinstance(date, datetime):
        raise TypeError('Given date must be instance of datetime.')

    return date + timedelta(0, 10**9)
