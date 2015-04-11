from datetime import datetime, timedelta

def add_gigasecond(time):
    return time + timedelta(seconds=10**9)
