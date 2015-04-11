from datetime import timedelta


def add_gigasecond(date):

    duration = timedelta(seconds=10**9)

    gigadate = date + duration

    return gigadate
