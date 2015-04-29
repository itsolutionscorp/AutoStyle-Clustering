import datetime

GIGASECOND = 10**9 / (60*60*24)

def add_gigasecond(date):
    date += datetime.timedelta(days = GIGASECOND)
    return date
