import datetime

def add_gigasecond(dt_stamp):
    return dt_stamp + datetime.timedelta(seconds=1000000000)
