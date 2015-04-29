from datetime import datetime, timedelta

def add_gigasecond(start_date):
    return start_date + timedelta(seconds = 10**9)
