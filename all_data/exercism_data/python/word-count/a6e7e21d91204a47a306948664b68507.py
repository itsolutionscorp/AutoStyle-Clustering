#!/usr/bin/python

import re

class Phrase:
    def __init__(self, text):
        self.text = text

    def word_count(self):
        dict = {}
        for word in re.split("[\s,\.!&@$%^&:]+", self.text.lower().strip(" ,.!&@$%^&:")):
            if word in dict:
                dict[word] += 1
            else:
                dict[word] = 1
        return dict
