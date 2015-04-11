class Anagram(object):
    "Another exercism exercise"

    def __init__(self, word):
        "Save word in cononical form"
        self.word = word.lower()
        self.anagram = self.canonize(word)

    def canonize(self, word):
        return sorted(word.lower())

    def match(self, word_list):
        "Produce a list of words that are the anagram of the class's word"
        anagrams = []
        for word in word_list:
            if self.word != word.lower() and self.anagram == self.canonize(word):
                anagrams.append(word)
        return anagrams
