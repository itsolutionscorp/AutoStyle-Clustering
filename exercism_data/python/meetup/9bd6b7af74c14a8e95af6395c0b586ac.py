# -*- coding: utf-8 -*-
"""
Created on Wed Oct  1 10:23:47 2014

@author: dwj26
"""
from datetime import date


def meetup_day(year, month, day, when):
    month_length = -(date(year, month, 1) - date(year, month + 1, 1)).days 
    days_in_month = [date(year, month, d) for d in range(1, month_length + 1)]
    possibles = [datie for datie in days_in_month if datie.strftime('%A') == day]
    count = 0    
    for i in ['1st','2nd','3rd','4th']:
        count += 1
        if i == when:
            when = count
            break
  
    if when == 'teenth':
        for d in possibles:
            if 13 <= d.day <= 19:
                return d
    elif when == 'last':
        return possibles[len(possibles) - 1]
    
    else:
        return possibles[when - 1]
    
    
