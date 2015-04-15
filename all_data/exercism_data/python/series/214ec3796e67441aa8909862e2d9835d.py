#!/usr/bin/env python3
#-*- coding: utf-8 -*-

__author__ = "Greg"

def slices(values, list_length):
    """
    Returns a list of lists of integers. The component lists are as long as the 
    integer 'list_length'. The component integers are converted from characters
    in the string 'values', and maintain order from 'values'.
    """
    
    num_lists = (len(values) - list_length + 1) 
    
    if num_lists < 1: 
        raise ValueError("Not enough digits (compared to list_length) to make a list")
    
    elif list_length < 1:
        raise ValueError(str(list_length) + " is an invalid number of lists")
    
    results = []
    
    for x in range(num_lists):
        slc = []
        for y in range(list_length):
            slc.append(int(values[y + x]))
        results.append(slc)
    return results
