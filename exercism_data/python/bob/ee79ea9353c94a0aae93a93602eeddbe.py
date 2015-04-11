"""Replys to statements as a lackadaisical teenager would"""
# October 23, 2014
# Written by Thomas Zumsteg
# Written for exercism.io

from re import search

def hey(what):
    """Replys to statements as a lackadaisical teenager would"""
    if is_yelling(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    elif is_nothing(what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

def is_question(what):
    """Test if what is a question"""
    # Tests if line ends in a questionmark
    # RegEx: (literal question mark)(any amout of whitespace)(end of line)
    return bool(search(r'\?\s*$', what))

def is_yelling(what):
    """Tests if what is yelled"""
    # Tests if what is all uppercase
    # Seems to support unicode
    return what.isupper()

def is_nothing(what):
    """Tests if what has no content"""
    # Tests for non-whitespace characters, returns inverse
    return not bool(search(r'\S', what))
