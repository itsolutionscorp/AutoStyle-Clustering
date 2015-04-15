'''
Created on Sep 24, 2014

@author: Adam Smith
'''

from datetime import timedelta

def add_gigasecond(date_obj):
    return date_obj + timedelta(seconds=10**9)
