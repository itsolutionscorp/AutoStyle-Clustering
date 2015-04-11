from datetime import timedelta

def add_gigasecond(startDate):
    gigasecond = 10**9
    return startDate + timedelta(seconds=gigasecond)
