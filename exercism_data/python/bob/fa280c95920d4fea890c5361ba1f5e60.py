# -*- coding: utf-8 -*-
"""Bob.

This module simulates conversation with an apathetic teenager.
"""

def hey(string):
    """Determine utterance type and return teenage response.
    
    Parameters:
        string -- the utterance Bob will respond to
    
    Return values:
        "Whoa, chill out!" -- response to shouting (allcaps)
        "Sure." -- response to questions (ends in ? or ?!)
        "Fine. Be that way!" -- response to silence (empty/whitespace)
        "Whatever." -- response to all other statements
    """
    if string.isupper():
        return "Whoa, chill out!"
    elif string.endswith(("?", "?!")):
        return "Sure."
    elif not string.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."
        
if __name__ == '__main__':
    string = raw_input('Talk to Bob: ')
    print hey(string)
