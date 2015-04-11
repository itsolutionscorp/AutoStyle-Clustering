# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 15:34:18 2014

@author: laegrim
"""

def slices(series, slice_size):
    '''
    Find all of the possible slices of slice size of consecutive 
    numbers in the series
    '''
    
    if slice_size > len(series) or slice_size == 0:
        raise(ValueError) 
    #break string into a list of incrementing chunks of slice size
    slices = [series[i:i+slice_size] for i in range(len(series)-slice_size+1)]
    #break each chunk in the list into a list of integer elements
    return [[int(slice_[i]) for i in range(slice_size)] for slice_ in slices]
