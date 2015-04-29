from datetime import datetime, timedelta

def add_gigasecond(date):
    gigasecond_date = date + timedelta(seconds=10**9)
    return gigasecond_date
