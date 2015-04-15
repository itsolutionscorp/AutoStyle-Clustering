#!/usr/bin/env python

import datetime

GIGASECOND_OFFSET = datetime.timedelta(seconds=10**9)


def add_gigasecond(starting_date):
    return starting_date + GIGASECOND_OFFSET


# vim:syn=python:et:
