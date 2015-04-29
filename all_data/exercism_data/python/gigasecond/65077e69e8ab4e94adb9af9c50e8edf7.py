from datetime import timedelta

gigasecond_delta = timedelta(seconds=10**9)


def add_gigasecond(birthday):
    return birthday + gigasecond_delta
