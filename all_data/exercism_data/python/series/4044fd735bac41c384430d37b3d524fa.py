#!/usr/bin/env python3
#-*- coding: utf-8 -*-

__author__ = "Greg"

def slices(values, list_size):
    """
    Returns a list of lists containing the characters from the string 'values' as
    long as the integer 'list_size'
    """
    
    num_lists = (len(values) - list_size + 1) 
    if num_lists < 1 or list_size <= 0:
        raise ValueError
    results = []
    for x in range(num_lists):
        slc = []
        for y in range(list_size):
            slc.append(int(values[y + x]))
        results.append(slc)
    return results
