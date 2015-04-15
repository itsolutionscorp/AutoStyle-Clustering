#!/usr/bin/env python3

from datetime import datetime, timedelta

def add_gigasecond(start_time):
    return start_time + timedelta(seconds=10**9)
