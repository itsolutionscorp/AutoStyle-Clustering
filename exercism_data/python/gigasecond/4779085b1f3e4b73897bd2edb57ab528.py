#!/usr/bin/python
from datetime import date
from datetime import timedelta
def add_gigasecond(start_datetime):
    return start_datetime + timedelta(0,1e9)
