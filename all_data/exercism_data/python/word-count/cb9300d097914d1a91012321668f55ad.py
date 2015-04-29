import re

class Phrase:

    def __init__(self, p):
        self.p = p

    def word_count(self):
      counts = {}
      for x in re.findall("\w+",self.p.lower()):
          counts[x] = counts.get(x,0) + 1
      print counts
      return counts
