# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 17:39:11 2014

@author: Earl Grey
"""
      
        
def hey(string):
    if string.strip() == "" or string == None:
        return "Fine. Be that way!"
    elif string.upper() == string and string.upper() != string.lower():
        return "Whoa, chill out!"
    elif string[-1] == "?":
        return "Sure."
    return "Whatever."

    
