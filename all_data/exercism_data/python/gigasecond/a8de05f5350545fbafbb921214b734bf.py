import datetime

def add_gigasecond(start_date):
    newdate = start_date + datetime.timedelta(seconds=1000000000)
    return newdate
