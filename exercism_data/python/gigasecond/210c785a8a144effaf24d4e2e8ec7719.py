import datetime

def add_gigasecond(birthday):
    giga = birthday + datetime.timedelta(0,(10**9))
    return giga
