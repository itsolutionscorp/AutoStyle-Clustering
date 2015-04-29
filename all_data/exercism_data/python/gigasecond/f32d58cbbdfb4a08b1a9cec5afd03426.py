# Python Exercism #6 Gigasecond

import datetime

def add_gigasecond(d1):
   
    delta = datetime.timedelta(seconds=10**9)
    return d1 + delta
