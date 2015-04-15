class Anagram(object):
    def __init__(self, word):
        split = list(word.lower())
        split.sort()
        self.word = split
        self.original = word
        
    def match(self, words):
        return [word for word in words if self.is_anagram(word)]
        
    def is_anagram(self, word):
        split = list(word.lower())
        split.sort()        
        if self.word == split:
             return self.original != word
        else:
            return False 
