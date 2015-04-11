from datetime import timedelta

def add_gigasecond(date):
    gigasecond = date + timedelta(0, 10**9)
    
    return gigasecond
