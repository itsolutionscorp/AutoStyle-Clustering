from datetime import timedelta

def add_gigasecond(birthday):
    return timedelta(seconds=1000000000) + birthday
