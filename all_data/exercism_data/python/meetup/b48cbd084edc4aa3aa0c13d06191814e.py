# -*- coding: utf-8 -*-
#
## Meetup

#Calculate the date of meetups.
#
#Typically meetups happen on the same day of the week.
#
#Examples are
#
# - the first Monday
# - the third Tuesday
# - the Wednesteenth
# - the last Thursday
#
#There are exactly 7 days that end in '-teenth'. Therefore, one is
#guaranteed that each day of the week will have a '-teenth' in every
#month.
#
#

# Meetup
from datetime import date
from datetime import timedelta

#borrowed from leap.py
def is_leap_year(leapyear):

    if leapyear % 4 == 0 :
        result = True
    if leapyear % 100 == 0 :
        result = False
    if leapyear % 400 == 0 :
        result = True
            
    return result

def calc_weekly(start_date, idx):
    
    #compare weekday numbers
    if start_date.weekday() <= idx:                
        week_date = start_date + timedelta(days=(idx - start_date.weekday()))
    else:
        week_date = start_date + timedelta(days=(idx - start_date.weekday()) + 7)
        
    return week_date
    
def meetup_day(year, month, dayStr, def_Str):
    
    #convert string to a number
    days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    day_idx = days.index(dayStr)    
    
    if def_Str == '1st':
            #calc_weekly takes first day of week in question
            meet = calc_weekly(date(year, month, 1), day_idx)
    elif def_Str == '2nd':
            #calc
            meet = calc_weekly(date(year, month, 8), day_idx)
    elif def_Str == '3rd':
            #calc
            meet = calc_weekly(date(year, month, 15), day_idx)
    elif def_Str == '4th':
            #calc
            meet = calc_weekly(date(year, month, 22), day_idx)       
    elif def_Str == 'last':
            #find last day of given month
            if month in (4,6,9,11):            
                max_day =  30
            elif month == 2:
                if is_leap_year(year):
                    max_day = 29
                else:
                    max_day = 28
            else:  
                max_day = 31
            
            #calc_weekly but backwards [no need to add 7!]
            meet = date(year, month, max_day)
            if meet.weekday() <= day_idx:                
                meet = meet + timedelta(days=(day_idx - meet.weekday()))
            else:
                meet = meet - timedelta(days=(day_idx - meet.weekday()))
    elif def_Str == 'teenth':
            #calc
            meet = calc_weekly(date(year, month, 13), day_idx)

    return meet
