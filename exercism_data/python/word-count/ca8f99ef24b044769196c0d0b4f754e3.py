import re

class Phrase(object):
    def __init__(self, phrase):
        self.rex = re.compile("\w+")
        if (phrase):
            self.phrase = phrase.lower().strip();
        else:
            self.phrase = ''


    def cache_result(func):

        results = {}
        def cacher(self):
            if not self.phrase in results:
                results[self.phrase] = func(self)

            return results[self.phrase]

        return cacher


    @cache_result
    def word_count(self):
        word_bag = {};

        for word in self.rex.findall(self.phrase):
            word_bag[word] = 1 + word_bag.get(word,0)

        return word_bag
