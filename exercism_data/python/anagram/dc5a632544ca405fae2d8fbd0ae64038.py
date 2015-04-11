class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.compressed = Anagram._compress(self.word)

  def match(self, candidates):
    matches = []
    for c in candidates:
      if self._matches_word(c):
        matches.append(c)
    return matches

  def _matches_word(self, c):
    return self.compressed == Anagram._compress(c) and c != self.word

  @staticmethod
  def _compress(word):
    return ''.join(sorted(word.lower()))
