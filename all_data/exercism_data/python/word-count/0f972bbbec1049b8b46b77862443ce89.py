import string

class Phrase:
    wordcounter = None 
    rawinput = None

    def __init__ (self, rawinput):
        self.rawinput = rawinput

    def __clean_words__ (self, dirtyInput):
        dirtyInput = dirtyInput.lower() #ignore case
        dirtyInput = dirtyInput.translate (string.maketrans ("",""), string.punctuation) #remove punctuation, for some reason not recommended for lower casing
        words = filter (None, dirtyInput.split (' '))
        return words

    def word_count (self):
        if not self.wordcounter:
            words = self.__clean_words__ (self.rawinput)
            self.wordcounter = {}
            for word in words:
                try:
                    self.wordcounter[word] += 1
                except KeyError, err:    
                    self.wordcounter[word] = 1
        return self.wordcounter

if __name__ == '__main__':
    phrase=Phrase ("test this test it good")
    print phrase.wordcounter
