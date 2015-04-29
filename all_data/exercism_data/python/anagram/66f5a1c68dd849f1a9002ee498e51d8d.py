class Anagram(object):

    def __init__(self, word):
        self.word = word

    def match(self, words):
        return [word for word in words if self.is_anagram(word)]

    def is_anagram(self, word):
        if word == self.word:
            return False
        else:
            return sorted(word.lower()) == sorted(self.word.lower())
