import re

class Phrase:

  def __init__(self,text):
    self.text = text

  def word_count(self):
    """ Split text stream by words, and return word counts """

    # Split by non-alphanumerical boundaires
    split_text = re.split('\W',self.text.lower())

    # Count occurences
    counts = {}
    for word in split_text:
      if word:
        if word in counts:
          counts[word] += 1
        else:
          counts[word] = 1

    return counts
