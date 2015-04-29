#!/usr/bin/python

def is_leap_year(input_year):
    if(not input_year % 4):
        if (input_year % 100 or not input_year % 400):
            return True
        else:
            return False
    else:
        return False
