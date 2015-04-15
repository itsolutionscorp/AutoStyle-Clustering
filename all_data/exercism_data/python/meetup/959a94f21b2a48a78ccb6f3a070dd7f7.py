# -*- coding: utf-8 -*-
"""
Created on Thu Oct  2 17:41:47 2014

@author: napopa
"""

import datetime

def meetup_day(year,month,dayofweek,meeting):
    if meeting == 'last':
        date = datetime.date(year,month+1,1)-datetime.timedelta(days=1)
        while True:        
            if date.strftime('%A') == dayofweek:
                return date
            else:
                date = date-datetime.timedelta(days=1)
    elif meeting in ('1st','2nd','3rd','4th'):
        i = 0
        k = int(meeting[0])
        date = datetime.date(year,month,1)
        while True:
            if date.strftime('%A') == dayofweek:
                i += 1
                if i == k:
                    return date
            date = date+datetime.timedelta(days=1)
    elif meeting == 'teenth':
        for i in range(13,20):
            date = datetime.date(year,month,i)
            if date.strftime('%A') == dayofweek:
                return date

        
    
