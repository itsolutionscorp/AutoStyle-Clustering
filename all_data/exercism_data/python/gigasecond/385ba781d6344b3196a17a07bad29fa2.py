from datetime import timedelta

def add_gigasecond(d):
    delta = timedelta(seconds=1000000000)
    return d + delta
