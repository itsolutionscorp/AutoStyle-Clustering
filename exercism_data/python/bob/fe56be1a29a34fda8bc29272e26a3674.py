#!/usr/bin/env python

"""Bob"""

__author__ = u'David Muse'
__date__ = u'2014-09-24'
__version__ = u'0.0.2'

def hey(obj):
    """
    Bob is a lackadaisical teenager.
    In conversation, his responses are very limited.
    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!'
    if you address him without actually saying anything.
    He answers 'Whatever.' to anything else.

    @param obj String
    @returns String
    @brief Hey
    """

    #
    # Blank (not actually saying anything)
    #

    if obj.replace('\t', ' ').strip() == '': # Check for white space
        return 'Fine. Be that way!'

    #
    # Yelling (note yelling is indicated by upper case and not '!'...)
    #

    elif obj.isupper(): # check for all upper case
        return 'Whoa, chill out!'

    #
    # Question
    #

    elif obj.endswith('?'): # check for question mark at the end
        return 'Sure.'

    #
    # Default response
    #

    else:
        return 'Whatever.'

def main():
    """
    @brief Main
    """

    import unittest

    import bob_test

    suite = unittest.TestLoader().loadTestsFromTestCase(bob_test.BobTests)
    unittest.TextTestRunner().run(suite)

if __name__ == '__main__':
    main()
