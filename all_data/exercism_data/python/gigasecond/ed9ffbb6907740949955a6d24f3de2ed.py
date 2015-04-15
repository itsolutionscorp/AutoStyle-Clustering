from datetime import date, timedelta

def add_gigasecond(base_date):
    return base_date + timedelta(seconds=10**9)
