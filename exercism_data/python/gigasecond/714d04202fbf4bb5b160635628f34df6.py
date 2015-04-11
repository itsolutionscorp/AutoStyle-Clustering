from datetime import timedelta

def add_gigasecond(birthday):
    g = timedelta(0, 10**9)
    return birthday + g
