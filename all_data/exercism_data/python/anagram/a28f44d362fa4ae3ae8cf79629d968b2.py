class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.sortedWord = self.sortLetters(word)
 
  def match(self, words):
    anagrams = []
    for word in words:
      sortedWord = self.sortLetters(word)  
      if sortedWord == self.sortedWord and self.word != word:
        anagrams.append(word)
    return anagrams

  def sortLetters(this, word):
    return ''.join(sorted(word.lower()))
