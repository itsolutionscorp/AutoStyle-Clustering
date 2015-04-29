#!/usr/bin/env python
# -*- coding: utf-8 -*-

from datetime import datetime, timedelta


def add_gigasecond(data):
    # return (datetime.combine(data, datetime.min.time()) + timedelta(seconds=10**9)).date()
    converted = datetime.combine(data, datetime.min.time())
    res = converted + timedelta(seconds=10**9)
    return res.date()


def add_gigasecond2(data):
    dt_ord = data.toordinal()
    return date.fromordinal(dt_ord + 10**9 / 86400)
