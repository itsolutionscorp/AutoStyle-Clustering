# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:46:39 2014

@author: Blair
"""
import calendar
from datetime import date
import time

def add_gigasecond(birthday):
    bDayInSeconds = calendar.timegm(birthday.timetuple())
    gigaDayInSeconds = bDayInSeconds + 10**9
    gigaDayDate = time.gmtime(gigaDayInSeconds)
    return date(gigaDayDate.tm_year, gigaDayDate.tm_mon, gigaDayDate.tm_mday)
    
    
