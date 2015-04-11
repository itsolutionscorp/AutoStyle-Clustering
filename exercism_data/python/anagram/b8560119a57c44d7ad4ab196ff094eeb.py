class Anagram(object):
    def __init__(self, word):
        self.word = word

    def match(self, canidates):
        main_word = sorted(list(self.word.lower()))

        anagrams = []

        for c in canidates:
            #Same words are not anagrams
            if self.word == c: continue

            comparor = sorted(list(c.lower()))
            if comparor == main_word:
                anagrams.append(c)

        return anagrams
