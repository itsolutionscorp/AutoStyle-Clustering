'''
:Date: 28-09-2013
:Version: 1
:Author: Jacznik Rodrigo
'''


from collections import Counter


class Anagram(object):
    '''
    An anagram is a type of word play, the result of rearranging the
     letters of a word or phrase to produce a new word or phrase, using
     all the original letters exactly once
     '''

    def __init__(self, word):
        '''
        Parameters
        ----------
        word: str
            A word without restriction
        '''

        self.word = word

    def match(self, list_word):
        '''
        Compare the letters in `self.word` with a possible anagram,
        if they match, add anagram to a list.

        Args
        ----
        list_word: list[str]
            list of possibles anagramas

        Returns
        -------
            the list with all anagrams of `self.word`
        '''

        char_dict = Counter(self.word.lower())
        return [anagram for anagram in self._filter(list_word) if
                    char_dict == Counter(anagram.lower())]

    def _filter(self, list_word):
        '''
        Filters out the words that have a different size that `self.word`
        and those who are equal to `self.word`.

        Parameters
        ----------
        list_word: list[str]
            list of words

        Return
        ------
        list[str]
             list of possibles anagrams
        '''

        len_word = len(self.word)
        return [possible_anagram for possible_anagram in list_word if
             len(possible_anagram) == len_word and
             self.word != possible_anagram]
