import datetime

def add_gigasecond(a_date):
    return a_date + datetime.timedelta(seconds=10**9)
