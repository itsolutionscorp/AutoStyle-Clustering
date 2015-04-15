#!/usr/bin/env python3

from datetime import timedelta
# Create a time delta/difference of a gigasecond.
gigasec_diff = timedelta(seconds=10 ** 9)


def add_gigasecond(d):
    return d + gigasec_diff
