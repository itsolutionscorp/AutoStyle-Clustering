import datetime

def add_gigasecond(date):
    return datetime.timedelta(seconds=10**9) + date
