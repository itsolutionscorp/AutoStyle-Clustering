import datetime

def add_gigasecond(date):
    elapsed = datetime.timedelta(seconds = 10**9)

    return date + elapsed
