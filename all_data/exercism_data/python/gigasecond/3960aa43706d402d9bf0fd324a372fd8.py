import datetime

def add_gigasecond(birthday):
    return birthday + datetime.timedelta(seconds=+10**9)
