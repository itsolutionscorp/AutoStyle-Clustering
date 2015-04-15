from datetime import timedelta

GIGASECOND = 1000000000

def add_gigasecond(date_):
    return date_ + timedelta(seconds=GIGASECOND)
