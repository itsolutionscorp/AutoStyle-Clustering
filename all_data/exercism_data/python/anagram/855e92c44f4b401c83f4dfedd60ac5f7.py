class Anagram(object):

    def __init__(self, word):
        self.word = word
        self.check = list(word.lower())
        self.check.sort()


    def match(self, words):
        matches = []
        for word in words:
            if word == self.word: continue
            if len(word) != len(self.word): continue
            check = list(word.lower())
            check.sort()
            if check == self.check:
                matches.append(word)

        return matches
