def alphagram(word):
    return sorted(word.lower())

class Anagram(object):

    def __init__(self, word):
        self.word = word
        self.alphagram = alphagram(word)
        self.lowercase = word.lower()

    def match(self, candidates):
        return [word for word in candidates if self.is_anagram(word)]

    def is_anagram(self, other):
        return self.matches(other) and not self.same_word(other)

    def matches(self, other):
        return self.alphagram == alphagram(other)

    def same_word(self, other):
        return self.lowercase == other.lower()
