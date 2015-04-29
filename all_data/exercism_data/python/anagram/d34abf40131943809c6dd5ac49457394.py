class Anagram(object):
   def __init__(self, word):
       self.word = word
       self.base = sorted(word.lower())

   def match(self, words):
       #return [word for word in words if self.base == sorted(word.lower()) and self.word != word]
       return filter(lambda word: self.base == sorted(word.lower()) and self.word != word, words)
