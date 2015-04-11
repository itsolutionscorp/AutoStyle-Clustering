#!/usr/bin/env python

class Year:
    def __init__(self, year):
        self.year = year
    
    def is_leap_year(self):
        if self.year % 4 == 0 and not self.year % 100 == 0 or self.year % 400 == 0:
            return True
        else:
            return False
