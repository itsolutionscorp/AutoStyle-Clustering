class Anagram(object):
    def __init__(self, word):
        self.word = word

    def is_anagram(self, word):
        if self.word == word:
            return False;
        return sorted(self.word.lower()) == sorted(word.lower());

    def match(self, words):
        matches = []
        for word in words:
            if self.is_anagram(word):
                matches.append(word)
        return matches;
