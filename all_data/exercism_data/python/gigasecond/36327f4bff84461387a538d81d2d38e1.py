from datetime import date, timedelta

GIGASECOND = 1000000000

def add_gigasecond(start_date):
    return start_date + timedelta(seconds = GIGASECOND)
