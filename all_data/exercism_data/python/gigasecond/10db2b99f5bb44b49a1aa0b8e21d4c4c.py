#!/usr/bin/python
# exercism python 6: gigasecond

import datetime

def add_gigasecond(date_obj):
    return date_obj + datetime.timedelta(seconds=10**9)
