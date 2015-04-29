from collections import defaultdict
import re

def word_count(string):
    words = re.split("\s+",string)
    m = defaultdict(lambda:0)
    for w in words:
        m[w]+=1
    return m
