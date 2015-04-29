class Anagram(object):
    def __init__(self, word):
        self.word = word

    def match(self, word_list):
        return [word for word in word_list if self.is_anagram(word)]

    def is_anagram(self, word):
        return self.alphagram(word) == self.alphagram(self.word) and \
            word != self.word

    @staticmethod
    def alphagram(word):
        return sorted(word.lower())
