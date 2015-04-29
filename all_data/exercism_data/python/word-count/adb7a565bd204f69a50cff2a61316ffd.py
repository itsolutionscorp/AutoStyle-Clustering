'''
Word Count

Author: Luke Shillabeer (lshillabeer@gmail.com)

This program implements the logic defined by the third problem (LEAP) in the 
exercism.io python problem-set.

TESTING:
Units tests for this code exist in word_count_test.py

REVISION HISTORY:
24/09/14: First implemented and commented the word_count function and
          wordcount module.

FEEDBACK:
http://exercism.io/submissions/0ea962b0c2d6adb3cdd9f659
'''

import re, string

def word_count(words):
    '''
    DESCRIPTION:
    For a given input string, counts the occurence of each word within the 
    string and keeps a tally. 

    Words can be delimited with any number of delimiters including commas,
    spaces and colons.

    ASSUMPTIONS:
    Assumes words are composed of latin alphabet and that all other characters
    are delimiters.

    Assumes that "word" and "word," are both expected to be counted as "word".

    ARGS:
    words - a string containing zero or more characters organised into words.

    RETURN:
    A dictionary object containing words as keys and values representing the
    tally for each word.

    EXAMPLES:
    >>> wordcount('one fish two fish red fish blue fish')
    {'one': 1, 'fish': 4, 'two': 1, 'red': 1, 'blue': 1}
    >>> 

    '''

    # this statement cleans the string of non-alphanumeric characters. Code
    # for this example found at; http://bit.ly/1B6mqPR
    words = re.sub('[\W_]+',' ', words)

    count_dict = {}

    # the test-cases treat all capitalisations as single instances of a word
    # therefore we apply the .lower() function.
    for word in words.lower().split():
        if  word in count_dict:
            count_dict[word] += 1
        else:
            count_dict[word] = 1
    return count_dict 
