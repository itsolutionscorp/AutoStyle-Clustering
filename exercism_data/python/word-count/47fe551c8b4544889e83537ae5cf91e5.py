import re

class Phrase:
    def __init__(self,phrase):
        self.words = {}
        phrase = re.sub( '[^\w\s]', '', phrase )
        for word in phrase.split():
            word = word.lower()
            if self.words.has_key( word ):
                self.words[word] = self.words[word]+1
            else:
                self.words[word] = 1

    def word_count(self):
        return self.words
