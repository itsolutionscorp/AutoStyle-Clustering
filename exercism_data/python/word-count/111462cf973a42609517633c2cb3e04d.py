import string

class Phrase(object):
   def __init__(self, phrase):
      self._phrase = phrase

   def word_count(self):
      """
      Count pre-filtered list of words.

      Note: dict function get allows a default if there's no matching key.
      """
      counts = {}
      for word in self._clean_phrase().split():
         counts[word] = counts.get(word,0) + 1
      return counts

   def _clean_phrase(self):
      """ 
      Clean non-character, non-digit, non-space characters from the phrase.
      """
      valid_chars = string.ascii_lowercase + string.digits
      clean_phrase = ''.join([x if x in valid_chars else ' ' 
         for x in self._phrase.lower()])
      return clean_phrase
