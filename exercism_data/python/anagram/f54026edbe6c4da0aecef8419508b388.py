class Anagram(object):
    def __init__(self, word):
        self._word = word
        self._letter_count = self._count(word)

    def _count(self, word):
        count = [0] * 26
        for l in word.lower():
            count[ord(l) - ord('a')] += 1
        return tuple(count)

    def match(self, words):
        def check_match(word):
            return (word != self._word and
                    self._count(word) == self._letter_count)
        return filter(check_match, words)
