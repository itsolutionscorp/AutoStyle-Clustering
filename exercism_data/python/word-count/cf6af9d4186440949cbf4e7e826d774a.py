'''
Created on Sep 24, 2014

@author: adsmith
'''
from collections import Counter
from re import findall

def word_count(phrase):
    return Counter(word.lower() for word in findall(r"\w+|\d+",phrase))
    # the built-in Counter object does basically everything this question asks
    # and rather than try to parse punctuation myself using something like
    # filter(None,map(lambda word: word.strip(string.punctuation).lower(), phrase.split()))
    # I thought it'd be better to just use regex. Readability over speed in Python!'

from string import punctuation

def word_count_no_counter(phrase):
    # just since this is such a no-brainer in Python, let's reinvent the wheel
    some_dict = {}
    for word in filter(None, map(lambda word: word.strip(punctuation).lower(),
                                 phrase.split())):
        some_dict[word] = some_dict.setdefault(word,0) + 1
    return some_dict
