# -*- coding: utf-8 -*-
from datetime import date, timedelta

def add_gigasecond(date_input):
    GIGA_SECOND = 10**9
    return date_input + timedelta(seconds = GIGA_SECOND)
