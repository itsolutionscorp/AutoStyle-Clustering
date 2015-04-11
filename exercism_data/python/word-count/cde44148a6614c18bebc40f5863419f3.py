from collections import Counter

class Phrase(object):
    def __init__(self, sentence):
        self.sentence = sentence

    def word_count(self):
        words = self.sentence.split(' ')
        alnums = [filter(lambda c: c.isalnum(), word.lower()) for word in words]
        return dict(Counter(filter(None, alnums)))
