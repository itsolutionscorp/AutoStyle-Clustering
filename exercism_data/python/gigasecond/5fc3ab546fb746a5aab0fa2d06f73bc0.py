from datetime import date
from datetime import datetime
from datetime import timedelta

def add_gigasecond(date):
    secondsPerDay = 60 * 60 * 24
    daysInGigaS = round(10**9/secondsPerDay)
    for i in range (daysInGigaS):
        date += timedelta(days=1)
    return(date)
