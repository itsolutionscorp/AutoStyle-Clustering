from datetime import timedelta

GIGASECOND = timedelta(0, 10**9)

def add_gigasecond(birthday):
    return birthday + GIGASECOND
