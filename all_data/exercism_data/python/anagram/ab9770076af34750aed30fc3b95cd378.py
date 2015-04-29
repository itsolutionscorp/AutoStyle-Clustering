class Anagram:

    def __init__(self, value):
        self.word = value

    def match(self, match_list):
        sorted_word = self.__word_to_letters(self.word)
        return [ item for item in match_list if item != self.word and self.__is_anagram( sorted_word, item)]

    def __is_anagram(self, compareTo, word ):
        return compareTo  == self.__word_to_letters(word)

    def __word_to_letters(self, value):
        l = sorted(value.lower())
        return "".join(l)
