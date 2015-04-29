'''
Created on Oct 2, 2014

@author: dulshani

This site contains a good intro to the datetime module
http://pymotw.com/2/datetime/index.html#module-datetime
'''
from datetime import timedelta

def add_gigasecond(start_date):
    gigasecond = timedelta(seconds=10**9)
    annivesary = start_date + gigasecond
    return annivesary
    
