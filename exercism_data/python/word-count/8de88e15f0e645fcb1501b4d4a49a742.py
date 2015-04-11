#!/usr/bin/env python

import re
from collections import Counter


class Phrase (object):
    def __init__(self, text):
        self.txt = text

    def word_count(self):
        #  [m.group(0).lower() for m in re.finditer(r'\w+', self.txt)]
        #  Unless we are going to cope with really huge input re.findall
        #  would be fineq
        return Counter([w for w in re.findall(r'\w+', self.txt.lower())])
