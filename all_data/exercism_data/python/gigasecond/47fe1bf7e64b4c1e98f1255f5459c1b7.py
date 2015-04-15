from datetime import timedelta

def add_gigasecond(dt):
    #  I think this is a huge cheat...
    return dt + timedelta(seconds=10**9)
