class Anagram(object):
    def __init__(self, word):
        self.word = word

    def match(self, word_list):
        sorted_word = sorted(self.word.lower())
        return [word for word in word_list if sorted(word.lower()) == sorted_word and word != self.word]
