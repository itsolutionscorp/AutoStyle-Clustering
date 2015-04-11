#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Write a program that will calculate the date that
someone turned or will celebrate their 1 Gs anniversary.

A gigasecond is one billion (10**9) seconds.
"""

from datetime import timedelta

__version__ = "0.0.2"
__all__ = ["__version__", "add_gigasecond"]


def add_gigasecond(date):
    """Returns a date 1 billion seconds from the given date

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.2

    :param date: the date to which to add 1Gs
    """
    return date + timedelta(seconds=10**9)
