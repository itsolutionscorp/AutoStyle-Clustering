# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 12:06:41 2014

@author: johann
"""
import datetime, calendar

def meetup_day(year, month, day, number):
    mydate = datetime.date(year,month,1)
    dict = {'Monday': 1, 'Tuesday' : 2, 'Wednesday' : 3 , 'Thursday' : 4, 'Friday' : 5, 'Saturday' : 6, 'Sunday' : 7}
    weekday = dict[day]
    
    
    if number.startswith(('1','2','3','4')):
        week = int(number[0]) - 1
        mydate +=  week * 7 * datetime.timedelta(days=1)
        return test_days(mydate,weekday)
    if number == 'teenth':
        mydate = mydate + datetime.timedelta(days=9)
        return test_days(mydate,weekday)
    if number == 'last':
        mydate = datetime.date(year,month,calendar.monthrange(year,month)[1])
        return test_days_backwards(mydate,weekday)


def test_days(mydate,weekday):
    while mydate.isoweekday() != weekday:
        mydate = mydate + datetime.timedelta(days=1)
    return mydate

def test_days_backwards(mydate,weekday):
    while mydate.isoweekday() != weekday:
        mydate = mydate - datetime.timedelta(days=1)
    return mydate
