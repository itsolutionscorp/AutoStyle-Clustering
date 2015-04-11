# this one should be extracted to its own module asap
def order_word(word):
  return ''.join(sorted(list(word.lower())))

class Anagram(object):
  def __init__(self, word):
    self.word = word
    self.ordered = order_word(word.lower())

  def match(self, words):
    return filter(lambda w: order_word(w) == self.ordered, words)
