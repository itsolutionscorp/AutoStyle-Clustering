#!/usr/bin/env python

from datetime import date

delta_sec = 10**9

def add_gigasecond(source_date):
    source_timestamp=int(source_date.strftime('%s'))
    result_timestamp=source_timestamp+delta_sec

    return date.fromtimestamp(result_timestamp)
