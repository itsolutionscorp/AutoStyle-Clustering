# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 23:13:22 2014

@author: johann
"""
import datetime
def add_gigasecond(date):
    return date + datetime.timedelta(seconds=10**9)
