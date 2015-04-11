# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
import datetime
def add_gigasecond(date):
    """Adds a gigasecond to a given date"""
    return date + datetime.timedelta(seconds=10**9)
