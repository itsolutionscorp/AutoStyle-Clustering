def detect_anagrams(word, words):

    return anagrams_detector(word).all_anagrams(words)


class anagrams_detector:

    lower_word = ""
    sorted_word = list()

    def __init__(self, word):
        self.lower_word = word.lower()
        self.sorted_word = sorted(self.lower_word)

    def is_anagram(self, word):
        return word.lower() != self.lower_word and sorted(word.lower()) == self.sorted_word

    def all_anagrams(self, words):
        return list(filter(self.is_anagram, words))
