from datetime import *

def add_gigasecond(date):
    GIGASECOND = 1000000000
    return date + timedelta(0, GIGASECOND)
