'''
Created on Sep 24, 2014

@author: dhawkins
'''
import datetime

def add_gigasecond(birthdate):
    one_gig = datetime.timedelta(seconds = 10**9)
    
    plus_time = birthdate + one_gig
    return plus_time
