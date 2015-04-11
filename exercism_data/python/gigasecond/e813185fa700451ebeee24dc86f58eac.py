""" gigasecond mdoule"""

from datetime import timedelta


def add_gigasecond(dateobj):
    """return date added gigasecond to"""
    return dateobj + timedelta(seconds=1000000000)
