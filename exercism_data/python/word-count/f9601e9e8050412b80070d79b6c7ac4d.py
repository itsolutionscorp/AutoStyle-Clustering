import re

class Phrase(object):
    '''
    Find and count all words in the provided phrase.
    
    It seems 'words' can contain numbers, but not special characters.
    '''
    
    _word_regex = re.compile("[A-Za-z0-9]+")
    
    def __init__(self, phrase):
        self._phrase = phrase
        self._word_index = self._make_index()
    
    def word_count(self):
        return self._word_index

    def _make_index(self):
        word_index = {}
        words = self._word_regex.findall(self._phrase)
        for word in words:
            word = word.lower()
            if word in word_index:
                word_index[word] += 1
            else:
                word_index[word] = 1

        return word_index
