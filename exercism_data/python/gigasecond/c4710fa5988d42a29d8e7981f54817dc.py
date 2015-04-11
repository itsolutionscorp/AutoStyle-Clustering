from datetime import date, timedelta

def add_gigasecond(a):
    return date.fromordinal(a.toordinal() + timedelta(0,10**9).days)
