import datetime

def add_gigasecond(Date):
    return Date + datetime.timedelta(seconds=pow(10,9))
