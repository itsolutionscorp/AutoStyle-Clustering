class word_count:
  def __init__(self, phrase):
    self.phrase = ' '.join(phrase.split())

  def count(self):
    data = {}
    words = self.phrase.split(" ")
    for w in words:
      if not w in data:
        data[w] = 1
      else:
        data[w] = data[w] + 1
    return data
