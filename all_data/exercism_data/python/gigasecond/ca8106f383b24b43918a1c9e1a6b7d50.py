from datetime import timedelta

def add_gigasecond(begin):
    return begin + timedelta(seconds=10**9)
