from datetime import timedelta

def add_gigasecond(date):
    gigasecond = timedelta(0, 10**9)
    return date + gigasecond
