#!/usr/bin/python
# exercism python #3: word count

import re
import collections

def word_count(sentence):
    word_list = re.findall('[0-9A-Za-z\!\&\@\$\%\^\:]+', sentence)
    word_dict = collections.defaultdict(int)
    for word in word_list:
        word_dict[word] += 1
    return word_dict
