#!/usr/bin/env python

import re
from collections import Counter


class Phrase (object):
    def __init__(self, text):
        self.txt = text

    def word_count(self):
        return Counter([m.group(0).lower() for m in re.finditer(r'\w+', self.txt)])
