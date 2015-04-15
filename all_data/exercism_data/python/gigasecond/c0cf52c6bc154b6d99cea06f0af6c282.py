from datetime import datetime, timedelta

def add_gigasecond(time):
    # time is a datetime object
    gigasecond = timedelta(0, 10**9)
    time += gigasecond

    return time
