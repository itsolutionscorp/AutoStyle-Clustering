#!/usr/bin/env python
def detect_anagrams(s, words):
    l = str.lower
    words = [w for w in words if (l(w) != l(s))]
    return [w for w in words if sorted(l(w)) == sorted(l(s))]
        
