# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 23:13:22 2014

@author: johann
"""
import datetime
def add_gigasecond(date):
    numdays = (10**9)/60/60/24
    date = date + datetime.timedelta(days=numdays)
    return date
