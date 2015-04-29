from datetime import timedelta

def add_gigasecond(to_date):
    return to_date + timedelta(seconds=10**9)
