#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import datetime


def add_gigasecond(d):
    gigasecond = datetime.timedelta(seconds=10**9)
    return d + gigasecond
