# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 02:39:45 2014

@author: laegrim
"""

from datetime import timedelta

gigasecond = timedelta(seconds = 10 ** 9)

def add_gigasecond(date):
    '''
    Return a date one gigasecond from the date given
    '''
    return date + gigasecond
    
