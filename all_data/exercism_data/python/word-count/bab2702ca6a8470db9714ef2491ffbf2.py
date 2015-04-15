import string
import collections

class Phrase:
    wordcounter = None 
    rawinput = None

    def __init__ (self, rawinput):
        self.rawinput = rawinput

    def _clean_words_ (self, dirtyinput):
        dirtyinput = dirtyinput.lower() #ignore case
        dirtyinput = dirtyinput.translate (string.maketrans ("",""), string.punctuation) #remove punctuation, for some reason not recommended for lower casing
        words = filter (None, dirtyinput.split (' '))
        return words

    def word_count (self):
        if not self.wordcounter:
            words = self._clean_words_ (self.rawinput)
            self.wordcounter = collections.Counter()
            for word in words:
                self.wordcounter[word] += 1
        return self.wordcounter

if __name__ == '__main__':
    phrase=Phrase ("test this test it good")
    print phrase.wordcounter
