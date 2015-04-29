import string
from collections import Counter
def word_count(self):
    self = self.lower().translate(self.maketrans('','',string.punctuation))
    words = self.split()
    return Counter(words)
