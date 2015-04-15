import string

class Phrase(object):

    def __init__(self, sentence):
        self.sentence = sentence.translate(string.maketrans("",""), string.punctuation)
        self.sentence = self.sentence.lower().split()

    def word_count(self):
        wordcount = {}
        for word in self.sentence:
            if word in wordcount:
                wordcount[word] += 1
            else:
                wordcount[word] = 1
        return wordcount
