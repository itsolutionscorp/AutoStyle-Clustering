#!/usr/local/bin/python

def hamming(var0, var1):
    diff = 0
    zipped = zip(var0, var1)

    if len(var0) > len(var1):
        diff += len(var0) - len(var1)
    if len(var0) < len(var1):
        diff += len(var1) - len(var0)
            
    for item0, item1 in zipped:
        if item0 != item1:
            diff += 1
    return diff
