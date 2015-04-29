import datetime

def add_gigasecond(start):
    end = start + datetime.timedelta(seconds=(10**9))
    return end
