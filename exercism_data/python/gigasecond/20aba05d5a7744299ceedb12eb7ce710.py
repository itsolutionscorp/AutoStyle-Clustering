import datetime

def add_gigasecond(start_date):
    d = datetime.timedelta(seconds = 10**9)
    new_date = start_date + d
    return new_date
