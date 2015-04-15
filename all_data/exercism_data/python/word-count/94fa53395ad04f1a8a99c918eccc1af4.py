import re
class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        counts = {}
        words = re.findall('[A-Za-z]+|[0-9]+', self.phrase.lower())
        for i in words:
            counts[i]  = counts.get(i,0) + 1
        return counts

    
