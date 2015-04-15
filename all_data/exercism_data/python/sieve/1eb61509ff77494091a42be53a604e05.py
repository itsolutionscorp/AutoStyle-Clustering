# -*- coding: utf-8 -*-
"""
Created on Tue Oct 21 10:03:41 2014

@author: Blair
"""

def sieve(maxInt):
    numList = [x for x in range(2, maxInt)]
    i = 0    
    
    while i < len(numList):
        for number in numList[i+1::]:
            if number % numList[i] == 0:
                numList.remove(number)
                
        i += 1
    
    return numList
