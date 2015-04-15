#
# Skeleton file for the Python "Bob" exercise.
#
PUNC_CHARS = '\t -=_+[]{}\|;":,./<>?!@#$%^&*()~`' + "'"
NUMBERS = '1234567890'

import re
def hey(what):
    if not what.strip():
        return "Fine. Be that way!"

    if is_all_upper(what) and not is_all_numeric(what):
        return "Whoa, chill out!"

    if what.endswith("?"):
        return "Sure."

    return "Whatever."

def is_all_upper(what):
    """
    Return True if all characters are the same as their uppercase
    representation, false otherwise.
    """
    for char in what:
        if char != char.upper():
            return False
    else: # nobreak, all upper
        return True

def is_all_numeric(what):
    """
    Return True if there are zero non-numeric non-punctuation
    characters in the string.
    """
    return not strip_numbers(strip_punctuation(what))

def strip_numbers(what):
    """
    Generator that returns only non-numeric characters in the string.
    """
    return ''.join([c for c in what if c not in NUMBERS])

def strip_punctuation(what):
    """
    Generator that returns only non-punctuation characters in the
    string.
    """
    return ''.join([c for c in what if c not in PUNC_CHARS])
