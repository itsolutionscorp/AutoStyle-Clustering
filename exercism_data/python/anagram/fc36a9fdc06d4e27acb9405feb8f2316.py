class Anagram(object):
    
    def __init__(self, word):
        self.word = word.lower()

    def match(self, phrase):
        self.phrase = phrase
        anagrams = [x for x in filter(lambda x: sorted(x.lower()) == sorted(self.word) and x.lower() != self.word, self.phrase)]
        return anagrams

