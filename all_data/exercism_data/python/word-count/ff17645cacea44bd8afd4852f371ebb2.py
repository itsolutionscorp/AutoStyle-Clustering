class Phrase:
    def __init__(self, s):
        self.words = [w for w in
            [''.join(c.lower() for c in w if c.isalnum()) for w in s.split(' ')]
                if w]
    def word_count(self):
        d = {}
        for word in self.words:
            if word not in d:
                d[word] = 0
            d[word] += 1
        return d
