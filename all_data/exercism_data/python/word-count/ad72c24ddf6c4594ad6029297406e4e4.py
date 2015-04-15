import string

class Phrase:
    wordcounter = {}

    def clean_words (self, letters):
        letters = letters.lower() #ignore case
        letters = letters.translate(string.maketrans("",""), string.punctuation) #remove punctuation
        letters = ' '.join(letters.split()) #remove contig whitespace
        words=letters.split(' ')
        return words

    def __init__(self,letters):
        words=self.clean_words(letters)
        self.wordcounter=self.do_count_words(words)

    def do_count_words(self, words):
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
