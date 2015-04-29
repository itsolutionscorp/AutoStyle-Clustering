#!/usr/bin/env python

from datetime import date, timedelta

secs_in_a_day = 60 * 60 * 24   # 60s * 60mins * 24hrs
days_in_1Gs = 1000000000 / secs_in_a_day    # decimal truncated by default

def add_gigasecond(date):
    return date + timedelta(days=days_in_1Gs)
