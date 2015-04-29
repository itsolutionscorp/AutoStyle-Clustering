from datetime import datetime,timedelta

def add_gigasecond(dt):
    try:
        return dt + timedelta(seconds=10**9)
    except:
        raise TypeError('Input was not a datetime.datetime object')
