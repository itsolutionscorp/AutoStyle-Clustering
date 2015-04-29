import datetime

def add_gigasecond(date):
    giga = date + datetime.timedelta(seconds=10**9)
    return giga
