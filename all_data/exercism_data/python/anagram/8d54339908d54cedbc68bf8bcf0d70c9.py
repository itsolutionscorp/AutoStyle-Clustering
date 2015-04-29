class Anagram:

    def __init__(self, value):
        self.word = value

    def match(self, match_list):
        matches = []
        sorted_word = self.__word_to_letters(self.word)
        for item in match_list:
            if self.__word_to_letters(item) == sorted_word \
                    and item != self.word:
                matches.append(item)
        return matches

    def __word_to_letters(self, value):
        l = list(value.lower())
        l.sort()
        return "".join(l)
