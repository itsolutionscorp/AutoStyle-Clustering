class WordCounter(dict):

    def __init__(self, iterable=None, **kwds):
        super(WordCounter, self).__init__()
        self.update(iterable, **kwds)

    def update(self, iterable, **kwds):
        self_get = self.get
        for elem in iterable.split():
            self[elem] = self_get(elem, 0) + 1

        if kwds:
            self.update(kwds)

def word_count(phrase):
    return WordCounter(phrase)
