#!/usr/bin/env python

from datetime import timedelta

def add_gigasecond(date):
    secs = 1000000000
    td = timedelta(seconds = secs)
    return date + td
