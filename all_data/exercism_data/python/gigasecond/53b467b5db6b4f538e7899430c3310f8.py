from datetime import timedelta

gigasecond = 10**9.


def add_gigasecond(time):
    return time + timedelta(seconds=gigasecond)
