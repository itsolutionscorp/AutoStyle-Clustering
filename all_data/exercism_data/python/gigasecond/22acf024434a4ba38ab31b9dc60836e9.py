from datetime import date, timedelta

def add_gigasecond(date):
    # days = 10**9/60/60/24
#     seconds = 10**9 - (days * 24 * 60 * 60)
    gigasecond = date + timedelta(0, 10**9)
    
    return gigasecond
