from datetime import datetime, timedelta

def add_gigasecond(birth_date):
    GIGASECOND = 10**9
    return birth_date + timedelta(seconds=GIGASECOND)
