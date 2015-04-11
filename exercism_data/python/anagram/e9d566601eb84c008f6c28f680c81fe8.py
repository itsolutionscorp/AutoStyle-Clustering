class Anagram(object):
   def __init__(self, word):
       self.word = word
       self.base = ''.join(sorted(list(word.lower())))

   def match(self, words):
       anagrams = []
       for word in words:
           base = ''.join(sorted(list(word.lower())))
           if self.base == base and self.word != word:
               anagrams.append(word)
       return anagrams
