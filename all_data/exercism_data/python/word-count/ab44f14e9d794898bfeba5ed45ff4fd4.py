from collections import defaultdict
import re

class Phrase:    
    def __init__(self, phrase):
        self.words = defaultdict(int)
        for word in phrase.split():
            normalized_word = re.sub('[\W_]+', '', word).lower()
            if normalized_word:
                self.words[normalized_word] += 1
        
    def word_count(self):
        return self.words
