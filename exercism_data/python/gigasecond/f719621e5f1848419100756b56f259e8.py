# -*- coding: utf-8 -*-

import time
from datetime import date

def add_gigasecond(dt):
    tmp = time.localtime(time.mktime(dt.timetuple()) + 10**9)
    return date(tmp.tm_year, tmp.tm_mon, tmp.tm_mday)
