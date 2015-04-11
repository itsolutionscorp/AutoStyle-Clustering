'''wordcount.py

    return dict with word:#word
'''

import string


class Phrase(object):
    '''Phrases class'''

    def __init__(self, text):
        '''Initialising Phrases, storing text'''
        self.text = text

    def word_count(self):
        '''Counting words in a text'''
        text = normalize_text(self.text)
        wordc = dict()
        for word in text:
            if not(word in wordc):
                wordc[word] = 0
            wordc[word] += 1
        return wordc


def normalize_text(text):
    '''Return list of words in text, only letters and digits'''
    s = string.ascii_lowercase + string.digits
    return ''.join(((i in s) and i) or " " for i in text.lower()).split()
