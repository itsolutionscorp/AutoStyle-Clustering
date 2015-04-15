class Phrase(object):
  def __init__(self, text):
    self.text = text
    self.extractor = WordExtractor()
    self.words = {}
    self._analyze_phrase()

  def _analyze_phrase(self):
    for word in self.text.split():
      self._analyze_word(word)

  def _analyze_word(self, word):
    extracted = self.extractor.extract(word)
    if extracted.isalnum():
      self._increment_word(extracted)

  def _increment_word(self, word):
    if not word in self.words:
      self.words[word] = 0
    self.words[word] += 1

  def word_count(self):
    return self.words

class WordExtractor(object):
  def __init__(self):
    self.delete_chars = self._bad_characters()

  def _bad_characters(self):
    return ''.join(c for c in map(chr, range(256)) if not c.isalnum())

  def extract(self, word):
    word = word.lower()
    word = word.translate(None, self.delete_chars)
    return word
