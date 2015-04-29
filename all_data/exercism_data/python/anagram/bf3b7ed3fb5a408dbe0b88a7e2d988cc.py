class Anagram:
    """ Takes a string and a list of words, then returns the ones
    that are anagrams"""
    def __init__(self, word):
        self.word = word

    def isAnagram(self, word, anagram):
        return word != anagram and ''.join(sorted(word.lower())) == ''.join(sorted(anagram.lower()))

    def match(self, anagramList):
        result = []
        for anagram in anagramList:
            if self.isAnagram(self.word, anagram):
                result.append(anagram)
        return result
