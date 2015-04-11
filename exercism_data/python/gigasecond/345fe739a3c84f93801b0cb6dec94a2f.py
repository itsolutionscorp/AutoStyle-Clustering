import datetime

def add_gigasecond(date):
    gs = datetime.timedelta(seconds = 10**9)
    return date + gs
