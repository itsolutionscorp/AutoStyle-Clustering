from collections import Counter


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = filter(self.__legal_char, phrase)

    @classmethod
    def __legal_char(cls, char):
        return char.isalnum() or char.isspace()

    def word_count(self):
        return Counter(self.phrase.lower().split())
