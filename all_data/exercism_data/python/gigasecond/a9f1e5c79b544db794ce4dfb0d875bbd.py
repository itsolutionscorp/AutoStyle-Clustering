import datetime

def add_gigasecond(d):
    dt = datetime.datetime(year = d.year, month = d.month, day = d.day)
    return (dt + datetime.timedelta(seconds=10**9)).date()
