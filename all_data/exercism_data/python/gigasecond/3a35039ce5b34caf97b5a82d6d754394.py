# -*- coding: utf-8 -*-
import datetime as dt


def add_gigasecond(birthDate):
    """
    Given someones date of birth, return their 1 gigasecond aniversery,
    which is date of birth plus one billion (10**9) seconds.
    """
    return birthDate + dt.timedelta(seconds=10**9)
