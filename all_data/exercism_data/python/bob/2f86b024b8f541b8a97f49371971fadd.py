# -*- coding: utf-8 -*-

import re

def hey(sentence_recieved):
    """ Returns an response to the given statement.

    Keyword arguments:
    sentence_recieved -- string representing the statement.

    NOTE: Order of checks is important. Yelling takes precedence over
    asking a question.
    """

    if sentence_recieved.isspace() or sentence_recieved == '':
        # Sentence is empty or just whitespace.
        return 'Fine. Be that way!'

    if sentence_recieved.isupper():
        # If all letters are capitalized, they are yelling.
        return 'Whoa, chill out!'


    if sentence_recieved[-1] == '?':
        # If a question mark is present at end of string, it's a question.
        return 'Sure.'

    # Unknown case, return 'Whatever.'
    return 'Whatever.'