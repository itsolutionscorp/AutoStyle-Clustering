# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
from datetime import timedelta
def add_gigasecond(date):
    """Adds a gigasecond to a given date"""
    return date + timedelta(seconds=10**9)
