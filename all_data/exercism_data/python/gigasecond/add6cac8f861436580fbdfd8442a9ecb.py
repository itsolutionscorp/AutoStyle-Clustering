import datetime
UNIT = 10 ** 9
def add_gigasecond(date):
    return date + datetime.timedelta(0,UNIT)
