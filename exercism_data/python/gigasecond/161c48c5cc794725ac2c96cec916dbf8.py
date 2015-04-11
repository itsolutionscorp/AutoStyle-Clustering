from datetime import date, timedelta

def add_gigasecond(dt):
    delta = timedelta(seconds = 1000000000)
    return dt + delta
