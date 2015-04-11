# -*- coding: utf-8 -*-

def is_leap_year(y):
    o=y%400
    if o==0:
        return True
    o=o%100
    if o==0:
        return False
    o=o%4
    if o==0:
        return True 
    return False
        
    
    
    
