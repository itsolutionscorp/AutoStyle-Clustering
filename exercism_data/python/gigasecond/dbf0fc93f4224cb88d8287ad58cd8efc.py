# -*- coding: utf-8 -*-

import datetime

def add_gigasecond(some_datetime):
    """
    add_gigasecond(datetime) -> datetime

    Return the given datetime moved one gigasecond into the future.
    """

    return some_datetime + datetime.timedelta(seconds = 10**9)
