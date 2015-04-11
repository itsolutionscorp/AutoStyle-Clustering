class Phrase(object):
  def __init__(self, text):
    self.text = text
    self.counts = dict()

  def word_count(self):
    map(self._loop, self._words())
    return self.counts

  def _words(self):
    return self.text.split(' ') 

  def _loop(self, word):
    return self._add(self._cleanup(word))

  def _add(self, word):
    if not word == "":
      self.counts[word] = self.counts.get(word, 0) + 1

  def _cleanup(self, word):
    return "".join([char for char in word if char.isalnum()]).lower()
