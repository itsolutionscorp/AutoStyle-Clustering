#!/usr/bin/env python
import re

def word_count(s):
    words = re.findall(r'\w+',s.lower())
    count={}
    for word in words:
        count[word]=count.get(word,0)+1
    return count
