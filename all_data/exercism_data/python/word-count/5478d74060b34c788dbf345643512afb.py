import re


class Phrase:

    def __init__(self, text):
        self.text = text

    def word_count(self):
        res = {}
        for w in [w.lower() for w in re.split('\W', self.text) if w]:
            res[w] = res[w] + 1 if w in res else 1
        return res
