from datetime import timedelta

def add_gigasecond(startTime):
    return startTime + timedelta(seconds=10**9)
