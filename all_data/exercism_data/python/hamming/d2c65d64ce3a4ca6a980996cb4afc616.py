# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 13:54:21 2014
"""



def hamming(a,b):
    mismatch_count = 0
    for x,y in zip(a,b):
        if x != y:
            mismatch_count += 1

    mismatch_count += abs(len(a) - len(b))

    return mismatch_count


