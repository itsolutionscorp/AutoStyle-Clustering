#!/usr/bin/env python3
""" module hamming for exercism.io programming training"""

def distance(a_string, b_string):
    """ function counts differences in strings"""
    assert len(a_string) == len(b_string)
    difference = [a != b for a, b in zip(a_string, b_string)]
    return sum(difference)
    
