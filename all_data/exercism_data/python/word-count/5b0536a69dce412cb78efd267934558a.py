class Phrase(object):
  def __init__(self, text):
    self.text = text
    self.words = WordExtractor().extract(text)
    self.counts = self._count_words()

  def _count_words(self):
    counts = {}
    for word in self.words:
      if word not in counts:
        counts[word] = 0
      counts[word] += 1
    return counts

  def word_count(self):
    return self.counts

class WordExtractor(object):
  @staticmethod
  def _nonprintable_chars():
    return ''.join(c for c in map(chr, range(256)) if not c.isalnum())

  def extract(self, words):
    found_words = []
    for word in words.split():
      extracted = self._extract_word(word)
      if extracted.isalnum():
        found_words.append(extracted)
    return found_words

  def _extract_word(self, word):
    return word.lower().translate(None, WordExtractor._nonprintable_chars())
