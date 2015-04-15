class Anagram:
    def __init__(self,word):
        self.word = word
        self.letters = self.word_to_letters(word)

    def word_to_letters(self,word):
        return sorted([ c.lower() for c in word ])

    def match(self,dictionary):
        anagrams = []

        for w in dictionary:
            if w.lower() == self.word.lower():
                continue

            if self.letters == self.word_to_letters(w):
                anagrams.append(w)


        return anagrams
