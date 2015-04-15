import unicodedata

class Phrase(object):
    def __init__(self, p):
        self.wc = dict()
        p = "".join(punct(r) for r in p)
        for w in p.split():
            try:
                self.wc[w.casefold()] += 1
            except KeyError:
                self.wc[w.casefold()] = 1
    def word_count(self):
        return self.wc

def punct(r):
    return " " if unicodedata.category(r)[0] in "ZPS" else r
