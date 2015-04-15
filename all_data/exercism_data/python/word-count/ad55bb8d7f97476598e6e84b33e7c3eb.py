import re
from collections import Counter

class Phrase(str):
    def word_count(self):
        return Counter(word.lower()
                for word in re.split('[^a-zA-Z0-9]+', self)
                if word)
