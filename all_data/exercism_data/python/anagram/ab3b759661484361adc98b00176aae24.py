class Anagram:

    def __init__(self, word):
        self.match_word = word

    def match(self, word_list):
        final_list = []
        words = [w for w in word_list if len(w) == len(self.match_word)]
        for word in words:
            if sorted(word.lower()) == sorted(self.match_word.lower()) and word != self.match_word:
                final_list.append(word)
        return final_list
