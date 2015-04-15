from datetime import timedelta, date

def add_gigasecond(birth):
    return birth + timedelta(seconds = 10**9)
