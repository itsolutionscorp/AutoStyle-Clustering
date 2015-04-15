from datetime import date, timedelta

def add_gigasecond(date):
    giga = timedelta(seconds=1000000000)
    return date + giga
