import datetime

def add_gigasecond(dt):
    return dt + datetime.timedelta(10**9 / 60 / 60 / 24)
