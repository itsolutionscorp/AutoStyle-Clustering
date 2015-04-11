import datetime

def add_gigasecond(dt):
    return dt + datetime.timedelta(seconds=10**9)
