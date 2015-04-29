# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:47:09 2014

@author: laegrim
"""

from datetime import date, timedelta
import re

def handle_last(last_day_of_month, weekday_string):
    '''
    Check backwards from last day of month to find last chosen weekday
    '''
    for i in range(last_day_of_month.day):
        day = last_day_of_month - timedelta(days = i)
        if day.strftime('%A') == weekday_string: return day
        
def handle_teenth(last_day_of_month, weekday_string):
    '''
    Check to find chosen weekday in the teenth date range
    '''
    for i in range(13, 20):
        day = last_day_of_month.replace(day = i)
        if day.strftime('%A') == weekday_string: return day
        
def handle_num(last_day_of_month, weekday_string, suffix):
    '''
    Check to find the nth chosen weekday of the month
    '''
    list_of_days = []
    num = int(re.sub('[a-zA-z]', '', suffix))
    for i in range(1, last_day_of_month.day + 1):
        day = last_day_of_month.replace(day = i)
        if day.strftime('%A') == weekday_string: list_of_days.append(day)
    return list_of_days[num - 1]
    
def meetup_day(year, month, weekday_string, suffix):
    '''
    Generate a date object representing the chosen weekday based on specified
    criteria in suffix
    '''
    last_day_of_month = date(year, (month + 1) % 12, 1) - timedelta(days = 1)
    if suffix == 'last': return handle_last(last_day_of_month, weekday_string)
    elif suffix == 'teenth': return handle_teenth(last_day_of_month, weekday_string)
    else: return handle_num(last_day_of_month, weekday_string, suffix)
    
    

    
