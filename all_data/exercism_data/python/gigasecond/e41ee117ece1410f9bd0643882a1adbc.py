# -*- coding: utf-8 -*-
"""
Created on Wed Oct  1 20:37:02 2014

@author: luis
"""

import datetime

def add_gigasecond(start):
    #convert gigasenconds to days
    days=(10**9)/(60*60*24)
    return start+datetime.timedelta(days=days)
