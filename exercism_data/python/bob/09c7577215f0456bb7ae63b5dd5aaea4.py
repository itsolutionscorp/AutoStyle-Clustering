#!/bin/python

import re

c = re.compile;

class Bob:
    def __init__(self):
        return

    reactions = [
        (c(u'\xe4'), 'Whatever.'),
        (c(r'\b(?!OK|DMV\b)[A-Z]{2}'), 'Woah, chill out!'),
        (c(u'\?(?!$)|^[0-9,][0-9, ]+$|[.!]$'), 'Whatever.'),
        (c(r'^\s*$'), 'Fine. Be that way!'),
    ]

    def hey(self, question):
        for check, answer in self.reactions:
            if check.search(question):
                return answer
            continue
        return 'Sure.'
