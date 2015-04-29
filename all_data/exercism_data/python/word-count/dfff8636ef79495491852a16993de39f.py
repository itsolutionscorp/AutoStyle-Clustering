import string
import collections

class Phrase:
    def __init__ (self, rawinput):
        self.rawinput = rawinput

    def _clean_words_ (self, dirtyinput):
        dirtyinput = dirtyinput.lower() #ignore case
        dirtyinput = dirtyinput.translate (string.maketrans ("",""), string.punctuation) #remove punctuation, for some reason not recommended for lower casing
        words = filter (None, dirtyinput.split (' '))
        return words

    def word_count (self):
        words = self._clean_words_ (self.rawinput)
        wordcounter = collections.Counter(words)
        return wordcounter

if __name__ == '__main__':
    print Phrase("test this test it good").word_count()
