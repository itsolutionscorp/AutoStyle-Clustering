import re


class Phrase:
      def __init__(self, inString):
          self.phrase = re.sub(r'[^\w^\d]',' ',inString.lower())
          self.phrase = re.sub(r'[\^]',' ',self.phrase)
      def word_count(self):
          phrase_list = self.phrase.split()
          d = {word:phrase_list.count(word) for word in phrase_list}
          return d
