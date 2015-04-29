'''
Created on Oct 17, 2014

@author: jbarni00
'''
from datetime import timedelta

def add_gigasecond(origindate):
    return origindate + timedelta(seconds=10**9)
