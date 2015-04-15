import math, datetime

GIGA = datetime.timedelta(seconds=math.pow(10,9))

def add_gigasecond(d):
    return d + GIGA
