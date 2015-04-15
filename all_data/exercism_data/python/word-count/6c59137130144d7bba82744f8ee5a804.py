import re

class Phrase(object):
    def __init__(self, sentence):
        self._sentence = sentence

    def word_count(self):
        """Return number of occurences of each word in the sentence"""
        count = {}
        for word in self._words():
            if word in count:
                count[word] += 1
            else:
                count[word] = 1

        return count

    def _words(self):
        """Return array of words in the sentence"""
        word = r'\w+'
        sentence = self._sentence.lower()
        return re.findall(word, sentence)
