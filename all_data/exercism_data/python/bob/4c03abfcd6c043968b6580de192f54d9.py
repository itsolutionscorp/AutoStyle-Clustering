# -*- coding: utf-8 -*-

def hey(userString):
    """
    Takes a string, and returns a string that represents Bob's reply.

    Args:
        userString (string): a valid string.

    Returns:
        string: Bob's reply.
    """

    if not userString or userString.isspace():
        return "Fine. Be that way!"
    
    elif userString.isupper():
        return "Whoa, chill out!"
    
    elif userString.endswith('?'):
        return "Sure."

    return "Whatever."
