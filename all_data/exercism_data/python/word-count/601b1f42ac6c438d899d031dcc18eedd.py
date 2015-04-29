import re

class Phrase:
    def __init__(self, phrase_string):
        self.phrase_string = phrase_string

    def word_count(self):
      dictionary = {}
      for word in self.phrase_string.split(" "):
        word = self.format_string(word)

        if word:
          dictionary = self.add_or_increment(dictionary, word)

      return dictionary

    def format_string(self, string):
      formatted = re.sub(r'\W+', '', string)
      return formatted.lower()

    def add_or_increment(self, dictionary, word):
      if not word in dictionary:
        dictionary[word] = 1
      else:
        dictionary[word] += 1
      return dictionary
