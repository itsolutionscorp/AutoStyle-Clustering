class Phrase(object):
    def __init__(self, string):
        self.cleanstring = ''.join(x for x in string if x.isalnum() or
                                   x.isspace())

    def word_count(self):
        wordlist = self.cleanstring.lower().split()
        counted = {word: wordlist.count(word) for word in set(wordlist)}
        return counted
