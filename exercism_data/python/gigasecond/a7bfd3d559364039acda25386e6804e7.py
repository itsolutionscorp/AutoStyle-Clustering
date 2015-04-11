from datetime import timedelta

GIGASECOND = 10**9

def add_gigasecond(date_from):
    return date_from + timedelta(seconds=GIGASECOND)
