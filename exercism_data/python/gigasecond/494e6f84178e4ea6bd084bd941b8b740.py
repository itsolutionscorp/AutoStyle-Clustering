#!/usr/bin/env python3
""" module gigasecond for exercism.io programming training"""

from datetime import date, timedelta


def add_gigasecond(in_date):
    """ function adds one giga second to date"""
    assert type(in_date) is date
    return in_date + timedelta(seconds=1000000000)
