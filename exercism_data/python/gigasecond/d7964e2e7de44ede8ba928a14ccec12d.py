# -*- coding: utf-8 -*-
import datetime as dt


def add_gigasecond(birthDate):
    """
    Calculate the date that someone turned or will celebrate their
    1 Gigasecond anniversary.

    1 Gs is one billion (10**9) seconds.

    Given someones date of birth, return the date they celebrate 1 Gs.
    """
    one_billion_seconds = dt.timedelta(seconds=10**9)
    return birthDate + one_billion_seconds
