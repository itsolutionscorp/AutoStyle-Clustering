class Anagram:
    def __init__(self, word):
        self.word = word.lower()

    def match(self, match_word):
        return_list = []
        for word in match_word:
            if word != self.word:
                if ''.join(sorted(self.word)) == ''.join(sorted(word.lower())):
                    return_list.append(word)
        return return_list
