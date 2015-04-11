import datetime

def add_gigasecond(date):
    secs = 10**9
    mins = secs/60
    hrs = mins/60
    days = hrs/24
    
    return date + datetime.timedelta(days)
