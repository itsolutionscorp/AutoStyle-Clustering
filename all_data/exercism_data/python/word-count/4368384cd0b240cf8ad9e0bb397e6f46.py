class Phrase(object):
  def __init__(self, text):
    self.text = text
    self.delete_chars = self._generate_bad_characters()
    self.words = {}
    self._analyze_phrase()

  def _generate_bad_characters(self):
    return ''.join(c for c in map(chr, range(256)) if not c.isalnum())

  def _analyze_phrase(self):
    for word in self.text.split():
      self._analyze_word(self._translate_word(word))

  def _translate_word(self, word):
    word = word.lower()
    word = word.translate(None, self.delete_chars)
    return word

  def _analyze_word(self, word):
    if not word.isalnum():
      return
    self._increment_word(word)

  def _increment_word(self, word):
    if not word in self.words:
      self.words[word] = 0
    self.words[word] += 1

  def word_count(self):
    return self.words
