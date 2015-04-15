import datetime

def add_gigasecond(date):
    s = 10**9
    now = date
    future = datetime.timedelta(seconds = s)
    day = now + future
    return day
