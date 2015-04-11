from datetime import date

import datetime

import calendar

def add_gigasecond(stuff):
    timestamp1 = calendar.timegm(stuff.timetuple())
    print timestamp1
    k = 10**9 +86400
    timestamp1 = timestamp1 + k
    print timestamp1
    return datetime.date.fromtimestamp(timestamp1)   
