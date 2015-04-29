from string import punctuation

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        counts = {}
        for word in self.phrase.lower().split():
            normalized = word.lower().strip(punctuation)
            if normalized:
                counts.setdefault(normalized, 0)
                counts[normalized] = counts[normalized] + 1
        return counts
