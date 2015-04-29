# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 10:36:25 2014

@author: Blair
"""

from datetime import date
import calendar

def meetup_day(year, month, dayOfWeek, descriptor):
    #year and month should be integers
    #dayOfWeek and descriptor should be strings

    searchCalendar = calendar.Calendar()

    #first, turn dayOfWeek into a number; monday = 0, sunday = 6
    daysDict = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6}
    desiredDay = daysDict.get(dayOfWeek.capitalize())
    
    monthList = searchCalendar.itermonthdays2(year, month)
    counter = 0
    
    for dateDayPair in monthList:
        #print dateDayPair
        if dateDayPair[1] == desiredDay and dateDayPair[0] > 0:
            #print "right day"
            counter += 1
            #if it's the right day of the week, check to see if the numbered date matches descriptor
            if descriptor[0].isdigit() and int(descriptor[0]) == counter:
                #print descriptor
                #print date(year, month, dateDayPair[0])
                return date(year, month, dateDayPair[0])
            elif descriptor == "1st" or descriptor.lower() == "first":
                #print "first"
                #print date(year, month, dateDayPair[0])
                return date(year, month, dateDayPair[0])
            elif descriptor == "teenth" and dateDayPair[0] >= 13 and dateDayPair[0] <= 19:
                #print date(year, month, dateDayPair[0])
                return date(year, month, dateDayPair[0])
            elif descriptor.lower() == "last" and counter > 4:
                #print date(year, month, dateDayPair[0])
                return date(year, month, dateDayPair[0])
            #print "wrong date"
                
    

    
