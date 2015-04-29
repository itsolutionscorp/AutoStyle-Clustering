#!/usr/bin/env python3
import collections

def word_count(in_string):
    counter = collections.Counter(in_string.split())
    return dict(counter)
    
