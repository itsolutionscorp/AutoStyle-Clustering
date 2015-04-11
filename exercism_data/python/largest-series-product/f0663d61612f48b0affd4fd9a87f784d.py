# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 21:17:08 2014

@author: johann
"""
import operator

def slices(number,myrange):
    number = str(number)
    list = []
    if myrange > len(number):
        raise ValueError
    for i in range(len(number)-myrange+1):
        tmplist = []
        for j in range(i,i+myrange):
            tmplist.append(int(number[j]))
        list.append(tmplist)
    return list


def largest_product(number, myrange):
    list = slices(number,myrange)
    sum = 0    
    for item in list:        
        if reduce(operator.mul,item,1) > sum:
            sum = reduce(operator.mul,item,1)
    return sum
