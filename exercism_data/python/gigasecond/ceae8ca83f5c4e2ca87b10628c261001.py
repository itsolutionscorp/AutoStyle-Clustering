from datetime import timedelta

GIGASECOND = 1e9


def add_gigasecond(birthday):
    return birthday + timedelta(seconds=GIGASECOND)
