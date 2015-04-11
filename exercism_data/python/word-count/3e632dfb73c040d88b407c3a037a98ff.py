import string


class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def words(self):
        words = self.phrase.split()
        words = [word.strip(string.punctuation) for word in words]
        words = [word.lower() for word in words]
        return [word for word in words if word]

    def word_count(self):
        words = self.words()
        return dict((word, words.count(word)) for word in words)
