# -*- coding: utf-8 -*-
"""Bob.

This module simulates conversation with an apathetic teenager.
"""

def hey(string):
    """Determine utterance type and return teenage response.
    
    Parameters:
        string -- the utterance Bob will respond to
    
    Return values:
        "Whoa, chill out!" -- response to shouting
        "Sure." -- response to questions
        "Fine. Be that way!" -- response to silence
        "Whatever." -- response to all other statements
    """
    if _is_shouting(string):
        return "Whoa, chill out!"
    elif string.endswith(("?", "?!")):
        return "Sure."
    elif _is_silence(string):
        return "Fine. Be that way!"
    else:
        return "Whatever."
  
def _is_shouting(string):
    # Strings are considered shouting if they are allcaps and are not an acronym.
    acronyms = ["OK"] # proof of concept -- could add others

    if not string.isupper():
        return False
    elif string in acronyms:
        return False
    else:
        return True
    
def _is_silence(string):
    # Strings are considered silence if they are empty or all whitespace.
    return not string.strip()
    
if __name__ == '__main__':
    string = raw_input('Talk to Bob: ')
    print hey(string)
