from datetime import date

def add_gigasecond(a):
    return date.fromordinal(a.toordinal() + 10**9 // (60*60*24))
