# -*- coding: utf-8 -*-
import datetime


def add_gigasecond(birthDate):
    """
    Given a DOB, return 1 gigasecond aniversery, DOB + 1 billion (10^9) seconds
    """
    return birthDate + datetime.timedelta(seconds=10**9)
