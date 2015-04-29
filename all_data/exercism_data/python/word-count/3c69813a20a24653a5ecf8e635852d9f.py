from collections import defaultdict
import re

def cache_result(func):
    results = {}
    def cacher(self):
        if not self.phrase in results:
            results[self.phrase] = func(self)

        return results[self.phrase]

    return cacher

class Phrase(object):
    rex = re.compile("\w+")

    def __init__(self, phrase):
        if phrase is None:
            self.phrase = ''
        else:
            self.phrase = phrase.lower().strip()

    @cache_result
    def word_count(self):
        word_bag = defaultdict(int);

        for word in Phrase.rex.findall(self.phrase):
            word_bag[word] = 1 + word_bag[word];

        return word_bag
