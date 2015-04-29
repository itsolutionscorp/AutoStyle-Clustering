class Anagram(object):
    def __init__(self, word):
        self.sorted = self.__sort_word(word)

    @classmethod
    def __sort_word(cls, word):
        return ''.join(sorted(word.lower()))

    def __is_match(self, word):
        return self.sorted == self.__sort_word(word)

    def match(self, words):
        return filter(self.__is_match, words)
