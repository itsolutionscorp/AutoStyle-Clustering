import re

class Anagram:
    def __init__(self, match_word):
        self.match_word = match_word

    def match(self, word_list):
        found_list = []
        for word in word_list:
            if self.matches(word):
                found_list.append(word)
        return found_list

    def matches(self, word):
        sorted_match_word = self.sort_letters(self.match_word)
        sorted_target_word = self.sort_letters(word)
        return (sorted_target_word == sorted_match_word) and (self.match_word != word)

    def sort_letters(self, word):
        word = word.lower()
        return ''.join(sorted(word))
