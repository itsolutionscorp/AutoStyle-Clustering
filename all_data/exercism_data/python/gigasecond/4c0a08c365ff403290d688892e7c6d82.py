from datetime import timedelta

def add_gigasecond(birthday):
    return birthday + timedelta(seconds=1*(10**9))
