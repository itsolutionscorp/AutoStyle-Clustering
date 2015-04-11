from __future__ import unicode_literals

def is_question(words):
    ''' Is this a question?
        Looks for a '?' at the end of the actual words.
    '''

    return words.strip().endswith('?')

def is_yelling(words):
    ''' Is this yelling?
        unicode / string .isupper() does the check we want.
        Case-able characters are upper case and there is
        at least one case-able character.
    '''

    return words.isupper()

def is_silence(words):
    ''' Is this silence
        Silence is a string that's just whitespace
    '''

    return False if words.strip() else True

def hey(words):
    ''' Bob's implementation of hey checks for his
        conversational triggers and responds appropirately.
    '''

    if   is_silence(words): return "Fine. Be that way!"

    elif is_yelling(words): return "Whoa, chill out!"

    elif is_question(words): return "Sure."

    else: return "Whatever."
