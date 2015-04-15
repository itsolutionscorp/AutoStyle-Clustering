from collections import Counter
import re

class Phrase(object):
    def __init__(self, string):
        self._wordcount = Counter(_words(string.lower()))

    def word_count(self):
        return dict(self._wordcount)

def _words(string):
    return (match.group(0) for match in re.finditer('\w+', string))
