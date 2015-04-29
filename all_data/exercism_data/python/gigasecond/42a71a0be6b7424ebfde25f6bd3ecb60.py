from datetime import timedelta

gigasecond_delta = timedelta(0, 10**9, 0)


def add_gigasecond(birthday):
    return birthday + gigasecond_delta
