# -*- coding: utf-8 -*-

def is_leap_year(inyear):
    """Returns True, if the input year is a leap year. 
    Otherwise retruns False"""
    
    isleap=False
    
    if not (inyear%4):
        isleap=True
        
        if not (inyear%100):
            isleap=False
            
            if not (inyear%400):
                isleap=True
                
    return isleap
