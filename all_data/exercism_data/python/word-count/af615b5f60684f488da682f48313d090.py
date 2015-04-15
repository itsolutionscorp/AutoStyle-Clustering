class Phrase(object):
   def __init__(self, phrase):
      self._phrase = phrase

   def word_count(self):
      """
      Count pre-filtered list of words.
      """
      from collections import Counter
      counts = Counter(self._replace_non_alnum().split())
      return counts

   def _replace_non_alnum(self):
      """ 
      Replace non-character, non-digit, non-space characters with spaces.
      """
      no_punct = [x if x.isalnum() else ' ' for x in self._phrase.lower()]
      return ''.join(no_punct)  # Convert an array of char to string
