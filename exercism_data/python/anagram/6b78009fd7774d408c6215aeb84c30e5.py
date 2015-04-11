'''anagram.py'''


class Anagram(object):
    '''Anagram class'''

    def __init__(self, text):
        '''init fo Anagram'''
        self.text = text

    def match(self, args):
        '''Return anagram matching strings in list '''
        sort_text = sort_word(self.text)
        return [word for word in args if
                ((sort_text == sort_word(word)) and  # word is anagram
                 (self.text != word))]               # same words not anagrams


def sort_word(word):
    '''sort word by alphabet: pass -> apss'''
    return "".join(sorted(word.lower()))
