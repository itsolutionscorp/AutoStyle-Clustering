import re


class Phrase:
    @staticmethod
    def words(string):
        return re.sub(r'\W+', ' ', string.lower()).split()
    
    def __init__(self, string_value):
        self.string_value = string_value

    def word_count(self):
        counts = {}
        for word in Phrase.words(self.string_value):
            if counts.has_key(word):
                counts[word] += 1
            else:
                counts[word] = 1
        return counts
            
