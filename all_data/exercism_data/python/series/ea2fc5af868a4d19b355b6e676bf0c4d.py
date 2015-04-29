# -*- coding: utf-8 -*-
# Series
#
#Write a program that will take a string of digits and give you all the possible consecutive number series of length `n` in that string.
#For example, the string "01234" has the following 3-digit series:
# - 012
# - 123
# - 234
#
#And if you ask for a 6-digit series from a 5-digit string, you deserve
#whatever you get {exception as ValueError}

def slices(NumSeries,length):

    if length < 1 or length > len(NumSeries):
        raise ValueError('Out of Bounds')
    
    sets = list()
    for s in range(0, len(NumSeries) - length + 1):
        t = NumSeries[s:(s + length)]
        x = [int(t[y]) for y in range(0,len(t))]
        sets.append(x)
        
    return sets
