# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 18:12:28 2014

@author: johann
"""
import re
from collections import Counter

def word_count(message):
    message = str(message)
    message = message.lower()
    message = re.sub("[^a-z 0-9]","",message)
    message = message.split()
    return Counter(message)
    
