# -*- coding: utf-8 -*-
"""
Created on Tue Oct 21 11:24:34 2014

@author: Blair
"""

class SumOfMultiples:
    
    def __init__(self, *arg):
        if len(arg) == 0:
            self.baseNums = [3, 5]
        else:
            self.baseNums = [x for x in arg]
            
    def to(self, maxNum):
        result = 0
        print self.baseNums
        
        for x in range(0, maxNum):
            for num in self.baseNums:
                if x % num == 0:
                    result += x
                    break
        
        return result
        

print SumOfMultiples().to(1000)
        
