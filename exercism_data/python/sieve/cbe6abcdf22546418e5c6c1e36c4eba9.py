# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 17:58:50 2014

@author: johann
"""
from math import sqrt
def sieve(num):
    list = range(num+1)
    list[0] = False
    list[1] = False
    for i in range(2,int(sqrt(num+1))+1):
        if list[i] is not False:
            for j in range(2*i,num+1,i):
                list[j] = False
    return filter(bool,list)
    
