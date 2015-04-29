# -*- coding: utf-8 -*-
"""
Created on Thu Oct 09 09:54:47 2014

@author: Blair
"""

def slices(seriesString, lengthInt):
    if lengthInt > len(seriesString) or lengthInt == 0:
       raise ValueError("Invalid series length.")
    
    possibilitiesList = []
    i = 0
    while i <= len(seriesString) - lengthInt:
        series = []
        for x in range(0, lengthInt):
            series.append(int(seriesString[i+x]))
        possibilitiesList.append(series)
        i += 1
    
    return possibilitiesList
    
def largest_product(productString, lengthInt):
    if lengthInt > len(productString):
        raise ValueError("Invalid product string length.")
        
    possibilitiesList = []
    i = 0
    while i <= len(productString) - lengthInt:
        product = 1
        for x in range(0, lengthInt):
            product = product * int(productString[i+x])
            
        possibilitiesList.append(product)
        i += 1
        
    return max(possibilitiesList)

print largest_product("0123456789", 2)
