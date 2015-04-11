# this one should be extracted to its own module asap
def order_word(word):
  return ''.join(sorted(word.lower()))

class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.ordered = order_word(word)

  def match(self, words):
    return [w for w in words if order_word(w) == self.ordered]
