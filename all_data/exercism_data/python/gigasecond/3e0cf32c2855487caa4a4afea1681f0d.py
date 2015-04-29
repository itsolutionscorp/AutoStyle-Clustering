# -*- coding: utf-8 -*-
"""
Created on Sun Sep 28 10:39:11 2014

@author: dwj26
"""
import matplotlib.dates as dates
import math

def add_gigasecond(date):
    thedate = dates.date2num(date) * 24 * 3600 #converts the date to a number of days, convert this to seconds
    inseconds = thedate + 10**9  #add the gigasecond
    indate = math.floor(inseconds/(24*3600))  #take the seconds and change it back into day value, round to the nearest day (that means flooring the date)
    return dates.num2date(indate).date()  #convert back to date and return as date format (instead of datetime)
    
    
    
    
