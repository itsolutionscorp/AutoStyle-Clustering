#gigasecond.py

import datetime

def add_gigasecond(bday):
    a=datetime.datetime(bday.year,bday.month,bday.day)
    adelta=datetime.timedelta(seconds=10**9)
    bday=a+adelta
    return datetime.date(bday.year,bday.month,bday.day)
