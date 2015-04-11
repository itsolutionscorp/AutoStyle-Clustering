from datetime import timedelta, datetime

def add_gigasecond(start_date):
    gigasecond = 10**9 
    return start_date + timedelta(seconds=gigasecond)
