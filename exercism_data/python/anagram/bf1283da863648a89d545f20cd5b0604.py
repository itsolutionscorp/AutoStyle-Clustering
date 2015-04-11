#!/usr/bin/env python

def detect_anagrams(s, words):
    low = str.lower
    sort = sorted
    words = [w for w in words if (low(w) != low(s))]
    return [w for w in words if sort(low(w)) == sort(low(s))]
        
