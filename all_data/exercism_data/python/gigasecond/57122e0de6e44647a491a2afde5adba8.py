import datetime

GIGASECOND = datetime.timedelta(0, 10**9)

def add_gigasecond(date):
    return date + GIGASECOND
