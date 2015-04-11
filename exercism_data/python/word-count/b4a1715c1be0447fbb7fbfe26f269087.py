class Phrase(object):
  def __init__(self, text):
    self.text = text
    self.words = self._extract_words(self.text)

  def _extract_words(self, text):
    words = []
    for word in text.split():
      words.append(self._filter_word(word))
    return filter(None, words)

  def _filter_word(self, word):
    return ''.join(filter(str.isalnum, word.lower()))

  def word_count(self):
    counts = {}
    for word in self.words:
      if not word in counts:
          counts[word] = 0
      counts[word] += 1
    return counts
