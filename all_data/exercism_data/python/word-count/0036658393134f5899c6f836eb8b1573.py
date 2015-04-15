import re

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = filter(None,[word.lower() for word in re.split('\W+', phrase)] )

    def word_count(self):
        dict = {}
        for word in self.phrase:
            if word in dict:
                dict[word] = dict[word] + 1
            else:
                dict[word] = 1
        return dict
