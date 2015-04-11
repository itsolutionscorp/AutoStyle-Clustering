from datetime import timedelta

def add_gigasecond(date):
    gigasecond = timedelta(seconds=10**9)

    return date + gigasecond
