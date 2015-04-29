import time
from datetime import date

def add_gigasecond(in_date):
    t = time.localtime(int(in_date.strftime("%s")) + 10**9)
    return date(t.tm_year, t.tm_mon, t.tm_mday)
