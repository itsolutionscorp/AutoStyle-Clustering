class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
        self.words = self.parse(self.phrase)

    def parse(self, words):
        raw = ''.join(c if c.isalnum() else ' ' for c in words).split()
        raw = [s.lower() for s in raw]
        return raw

    def word_count(self):
        words = []
        freq = []
        for word in self.words:
            if word not in words:
                words.append(word)
                freq.append(1)
            else:
                freq[words.index(word)] = freq[words.index(word)] + 1
        return dict(zip(words, freq))
