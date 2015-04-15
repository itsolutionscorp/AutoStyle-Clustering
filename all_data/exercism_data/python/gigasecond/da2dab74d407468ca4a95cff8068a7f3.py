'''
Created on Oct 2, 2014

@author: bennettr
'''
import datetime

def add_gigasecond(born):
    Anniversary = born + datetime.timedelta(0,10**9)
    return Anniversary
    
