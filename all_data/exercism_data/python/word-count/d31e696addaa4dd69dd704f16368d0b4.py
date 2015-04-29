from collections import defaultdict
import re

def cache_result(func):
    results = {}
    def cacher(*args):
        if args not in results:
            results[args] = func(*args)

        return results[args]

    return cacher


class Phrase(object):
    _rex = re.compile("\w+")

    def __init__(self, phrase):
        if phrase is None:
            phrase = ''

        self.phrase = phrase.lower().strip()

    @cache_result
    def word_count(self):
        word_bag = defaultdict(int);

        for word in self.__class__._rex.findall(self.phrase):
            word_bag[word] = 1 + word_bag[word];

        return word_bag
