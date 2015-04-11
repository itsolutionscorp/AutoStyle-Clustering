#!/usr/bin/env python
from datetime import date
from math import *

def meetup_day(year,month,day,position):
    first = date(year,month,1)
    week = {}
    week['Monday'] = 0
    week['Tuesday'] = 1
    week['Wednesday'] = 2
    week['Thursday'] = 3
    week['Friday'] = 4
    week['Saturday'] = 5
    week['Sunday'] = 6

    first = firstDay(first, week[day])
    ret_day = getDay(first, position)
    return date (year,month,ret_day)

def firstDay(query, day):
    # Mon = 0, Tues = 1, Weds = 2, Thur = 3, Fri = 4, Sat = 5, Sun = 6
    day_of_week = query.weekday()
    # The first of the month happens to be the day of the week we want
    if day_of_week == day:
        return date(query.year, query.month, 1)
    ##
    # We've already passed the day we want in the first week.
    # 1st + 7 - day_of_week = Monday.
    # Monday + day = day we want
    if day_of_week > day:
        return date(query.year,query.month, 8 - day_of_week + day)
    ##
    # We still need to get to the day
    # 1st + day - day of week
    else:
        return date(query.year,query.month, 1 + day - day_of_week)

def getDay(first, position):
    if first.month == 2:
        if isLeapYear(first.year):
            max_days = 29
        else:
            max_days = 28
    elif ((first.month < 8 and first.month % 2) or 
          (first.month >= 8 and not first.month % 2)):
        max_days = 31
    else:
        max_days = 30

    if position == '1st':
        return first.day
    elif position == '2nd':
        return (first.day + 7)
    elif position == '3rd':
        return (first.day + 14)
    elif position == '4th':
        return (first.day + 21)
    elif position == 'teenth':
        if first.day <= 5:
            return (first.day + 14)
        else:
            return (first.day + 7)
    elif position == 'last':
        return (((max_days -first.day)/7) * 7 + first.day)

def isLeapYear(year):
    if not year % 4:
        if (not(year % 100) and (year % 400)):
            return False
        return True
    return False
