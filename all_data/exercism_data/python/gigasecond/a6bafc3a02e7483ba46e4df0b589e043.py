from datetime import date, timedelta

def add_gigasecond(birthday):
    GIGASECOND = timedelta(seconds=10**9)
    return birthday+GIGASECOND
