from datetime import datetime, timedelta

def add_gigasecond(birthday):
    gigasecond = 10**9
    return birthday + timedelta(seconds=gigasecond)
