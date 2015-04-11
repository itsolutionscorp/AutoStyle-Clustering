# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 20:52:12 2014

@author: johann
"""

class SumOfMultiples():
      
    def __init__(self,*args):
        self.multiples = list(args)
        if len(args) == 0:
            self.multiples = [3,5]
            
    def to(self,number):
        list = range(1,number)
        newlist =[]
        for value in list:
            for multiple in self.multiples:
                if value % multiple == 0 and value not in newlist:
                    newlist.append(value)
        return sum(newlist)
                    
    
