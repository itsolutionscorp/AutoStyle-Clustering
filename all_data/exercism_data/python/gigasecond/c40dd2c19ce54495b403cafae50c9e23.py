import datetime


def add_gigasecond(date):
    gigasec = datetime.timedelta(0,10**9)
    return date+gigasec
