from datetime import date, timedelta

GS = timedelta(seconds=1e9)

def add_gigasecond(date):
    return date + GS
