import datetime

def add_gigasecond(startdate):
    return startdate + datetime.timedelta(seconds=10**9)
