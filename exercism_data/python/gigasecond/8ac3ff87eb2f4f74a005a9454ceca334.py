# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)
from datetime import datetime
import time


def add_gigasecond(birthdate):
    """Transforms the date into a unix timestamp, adds a giga second and
       returns the new date"""
    giga_time = int(time.mktime(birthdate.timetuple())) + 10**9
    return datetime.fromtimestamp(giga_time).date()
