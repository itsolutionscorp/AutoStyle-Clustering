''' This module is for the Python "Bob" exercise in exercism.io'''

import re
import string
import unicodedata


def hey(what):
    ''' This function will take a unicode string and return a string depending
        on the input as specified in exercism.io python exercise 'bob'.
        If a question is asked, then 'Sure.' is returned.
        If you yell, then 'Whoa, chill out!' is returned.
        If you address the program without saying anything, 'Fine. Be that way!'
        is returned.
        And for everything else, 'Whatever.' is returned.
    '''

    def remove_accents(input_str):
        ''' This function takes a string as input and converts it to unicode
            normal KD (NFKD) form.
        '''

        nkfd_form = unicodedata.normalize('NFKD', input_str)
        return u"".join([c for c in nkfd_form if not unicodedata.combining(c)])

    def shouting(phrase):
        ''' This function takes a set as input and identifies whether the
            elements in the set are limited to those used for 'shouting'.
            The set for 'shouting' was defined as all digits, punctuation,
            and whitespace along with at least 1 uppercase letter.
        '''

        set_phrase = phrase.difference(string.digits,
                                        string.punctuation,
                                        string.whitespace,
                                    )
        if set_phrase:
            if set_phrase.difference(string.uppercase):
                return False
            else:
                return True
        else:
            return False

    test_set = set(remove_accents(what))

    if not test_set - set(string.whitespace):
        return u'Fine. Be that way!'
    elif re.search(r"\?\s*$", what):
        if shouting(test_set):
            return u'Whoa, chill out!'
        else:
            return u'Sure.'
    elif shouting(test_set):
        return u'Whoa, chill out!'
    else:
        return u'Whatever.'
