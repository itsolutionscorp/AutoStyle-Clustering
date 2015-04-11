import re
from collections import defaultdict
get_words = re.compile("\w+").findall

class Phrase(object):
    def __init__(self, v):
        self.words = [w.lower() for w in get_words(v)]

    def word_count(self):
        result = defaultdict(int)

        for word in self.words:
            result[word] += 1

        return result
