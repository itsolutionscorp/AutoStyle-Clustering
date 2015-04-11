# -*- coding: utf-8 -*-

from __future__ import unicode_literals

import re

def word_count(phrase):
    regex = re.compile(r'[!@£$%^&*:,]')
    words = regex.sub('', phrase)
    words = words.split()

    counter_dict = {}
    for word in words:
        w = word.lower()
        try:
            counter_dict[w] = counter_dict[w] + 1
        except KeyError:
            counter_dict[w] = 1

    return counter_dict
