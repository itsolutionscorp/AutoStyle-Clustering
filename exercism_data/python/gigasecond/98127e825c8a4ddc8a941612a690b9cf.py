from datetime import timedelta

GIGASEC = timedelta(seconds=10**9)

def add_gigasecond(d):
    return d + GIGASEC