import string
__author__ = "Bill Davis"

'''
    Two Solutions
'''    


import time
import operator


def distance(str1, str2):
    assert len(str1) == len(str2)
    diffs = 0
    for ch1, ch2 in zip(str1, str2):
        if ch1 != ch2:
            diffs += 1
    return diffs    

'''    
def distance(str1, str2):
    assert len(str1) == len(str2)
    return sum(map(operator.ne, str1, str2)) 
'''    
    
