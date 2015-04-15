from datetime import timedelta


def add_gigasecond(d):
    """Adds a gigasecond to the given date `d`."""
    return d + timedelta(seconds=10**9)
