class Anagram:
    def __init__(self, word):
        self.word = word.lower()

    def match(self, match_word):
        return_list = []
        for word in match_word:
            if word != self.word:
                try:
                    [''.join(sorted(self.word))].remove(
                        ''.join(sorted(word.lower())))
                    return_list.append(word)
                except ValueError:
                    pass
        return return_list
