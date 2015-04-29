"""
Module implementing the gigasecond task from exercism.
"""

from datetime import timedelta

BILLION_SECONDS = 10 ** 9


def add_gigasecond(birth_date):
    """
    Return dt + 1 gigasecond.
    """
    return birth_date + timedelta(seconds=BILLION_SECONDS)
