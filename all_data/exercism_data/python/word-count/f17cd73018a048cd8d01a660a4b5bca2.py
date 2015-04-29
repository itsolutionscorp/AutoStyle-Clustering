from collections import Counter

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        phrase = "".join([i.lower() if i.isalnum() else " " for i in self.phrase])
        return dict(Counter(phrase.split()))
