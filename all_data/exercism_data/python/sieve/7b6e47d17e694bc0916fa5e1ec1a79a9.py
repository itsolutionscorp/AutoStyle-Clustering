#!/usr/bin/env python
from math import *

def sieve(max_range):
    full_list = range(2,max_range+1)
    new_list = []
    
    while len(full_list):
        test_item = full_list.pop(0)
        for item in full_list:
            if not item % test_item:
                full_list.remove(item)

        new_list.append(test_item)
    return new_list
