import string

class Phrase:
    wordcounter = {}

    def __init__(self,letters):
        letters=letters.lower() #ignore case - doesn't need its own fun
        letters= letters.translate(string.maketrans("",""), string.punctuation) #remove punctuation. Doesn't need a fun
        letters = ' '.join(letters.split()) #remove contig whitespace, right here
        words=letters.split(' ')
        self.wordcounter=self.countwords(words)

    def countwords(self, words):
        wordict={}
        for word in words:
            try:
                wordict[word] += 1
            except KeyError, err:    
                wordict[word] = 1
        return wordict

    def word_count(self):
        return self.wordcounter

if __name__ == '__main__':
    phrase=Phrase("test this test it good")
    print phrase.wordcounter
