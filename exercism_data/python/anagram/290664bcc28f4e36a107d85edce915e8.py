class Anagram:
  def __init__(self, word):
    self.word = word.lower()
    self.sortedWord = self._sort(word)

  def match(self, wordList):
    result = []
    for i in wordList:
      if self._sort(i) == self.sortedWord and i.lower() != self.word:
        result.append(i)
    return result

  def _sort(self, word):
    return ''.join(sorted(word.lower()))
