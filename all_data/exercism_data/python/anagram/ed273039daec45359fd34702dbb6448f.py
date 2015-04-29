class Anagram(object):
  def __init__(self, word):
    self.word = word.lower()
    self.sword = sorted(self.word)

  def match(self, words):
    return list(self.imatch(words))

  def imatch(self, words):
    for word in words:
      lword = word.lower()
      if (lword!=self.word and sorted(lword) == self.sword):
        yield word
