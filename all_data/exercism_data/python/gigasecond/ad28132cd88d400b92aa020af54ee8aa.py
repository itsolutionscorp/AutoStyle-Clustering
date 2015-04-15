from datetime import timedelta


def add_gigasecond(birthday):
    gs = 10**9
    return birthday + timedelta(seconds=gs)
