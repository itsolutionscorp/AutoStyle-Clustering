#!/usr/bin/env python
from datetime import date
from math import *

def add_gigasecond(birthday):
    bday = updateDate(birthday)
    bday.setToNewYear()
    return bday.incrementSecs(1000000000)



class updateDate:
    def __init__(self, basedate):
        self.basedate = basedate
        self.year = basedate.year
        self.month = basedate.month
        self.day = basedate.day
        self.secs = 0

    def _isLeapYear(self, year):
        if type(year) is not int:
            print "Value provided is not an int."
            raise ValueError

        if not year % 4:
            if (not(year % 100) and (year % 400)) :
                return False
            return True
        return False

    def setToNewYear(self):
        self.secs = self.secs + (self.day - 1)*24*60*60
        self.day = 1
        for month in range(1,self.month):
            if month == 2:
                if self._isLeapYear(self.year):
                    self.secs = self.secs + 29*24*60*60
                else:
                    self.secs = self.secs + 28*24*60*60
            elif(((month < 8) and (month % 2)) or 
                 ((month > 8) and not (month % 2))):
                self.secs = self.secs + 31*24*60*60
            else:
                self.secs = self.secs + 30*24*60*60
        self.month = 1

    def incrementSecs(self, secs):
        if type(secs) is not int:
            print "Value provided is not an int."
            raise ValueError
        self.secs = self.secs + secs
        self._updateYear()
        self._updateMonth()
        self._updateDay()
        return date(self.year, self.month, self.day)


    def _updateYear(self):
        while self._yearRemainder():
            if self._isLeapYear(self.year):
                self.secs = self.secs - 366*24*60*60
                self.year = self.year + 1
            else:
                self.secs = self.secs - 365*24*60*60
                self.year = self.year + 1

    def _updateMonth(self):
        for month in range(self.month, 13):
            secs = 0
            if month == 2:
                if self._isLeapYear(self.year):
                    secs = 29*24*60*60
                else:
                    secs = 28*24*60*60
            elif(((month < 8) and (month % 2)) or 
                 ((month > 8) and not (month % 2))):
                secs = 31*24*60*60
            else:
                secs = 30*24*60*60

            if self.secs >= secs:
                self.secs = self.secs - secs
            else:
                self.month = month
                break
                    

    def _updateDay(self):
        self.day = self.day + int(self.secs/(24*60*60)) 

    def _yearRemainder(self):
        if self._isLeapYear(self.year):
            if self.secs >= 366*24*60*60:
                return True
            else:
                return False
        else:
            if self.secs >= 365*24*60*60:
                return True
            else:
                return False
