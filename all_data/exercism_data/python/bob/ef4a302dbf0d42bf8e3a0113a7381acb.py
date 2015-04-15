# -*- coding: utf-8 -*-

def hey(userString):
    """
    Takes a string, and returns a string that represents Bob's reply.

    Args:
        userString (string): a valid string.

    Returns:
        string: Bob's reply.
    """

    if userString.isspace() or len(userString) == 0:
        return "Fine. Be that way!"
    elif userString.isupper():
        return "Whoa, chill out!"
    elif userString[-1] == '?':
        return "Sure."

    return "Whatever."
