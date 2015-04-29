class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.sortedWord = ''.join(sorted(word.lower()))
 
  def match(self, words):
    anagrams = []
    for word in words:
      sortedWord = ''.join(sorted(word.lower()))
      if sortedWord == self.sortedWord and self.word != word:
        anagrams.append(word)
    return anagrams
