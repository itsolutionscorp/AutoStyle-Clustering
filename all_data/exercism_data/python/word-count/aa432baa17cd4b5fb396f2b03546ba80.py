from collections import defaultdict

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        d = defaultdict(int)
        phrase = "".join([i if i.isalpha() or i.isdigit() else " " for i in self.phrase])
        for i in phrase.split():
            if len(i)>0: d[i.lower()] += 1
        return dict(d)
