class Anagram:
    def __init__(self, word):
        self.word = word

    def match(self, candidates):
        # Lowercase the word here to avoid doing it with every candidate.
        lword = self.word.lower()

        return [x for x in candidates if self.is_anagram(lword, x.lower())]

    def is_anagram(self, word, candidate):
        # Mismatched lengths or the same word are automatically not a
        # match.
        if len(candidate) != len(word) or candidate == word:
            return False

        for y in word:
            i = candidate.find(y)

            if i >= 0:
                # Strings are immutable... have to drop the index other
                # ways.
                candidate = ''.join([candidate[:i], candidate[i+1:]])
            else:
                return False

        return True
