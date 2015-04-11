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

    # Ensure at least one letter is present.
    is_letter_present = re.compile('[a-zA-Z]')
    if (
            is_letter_present.search(sentence_recieved) and 
            sentence_recieved.upper() == sentence_recieved
        ):
        # If all letters are capitalized, they are yelling.
        return 'Whoa, chill out!'

    index = sentence_recieved.find('?') 
    if index and index == (len(sentence_recieved) - 1):
        # If a question mark is present at end of string, it's a question.
        return 'Sure.'

    # Unknown case, return 'Whatever.'
    return 'Whatever.'
