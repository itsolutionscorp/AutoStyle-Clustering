class word_count:
  def __init__(self, phrase):
    self.phrase = phrase

  def count(self):
    words = self.phrase.split()
    return {w : words.count(w) for w in words}
