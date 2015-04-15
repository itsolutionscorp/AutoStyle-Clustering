#!/bin/env python
# -*- coding: latin-1 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    So we'te attempting to look like a teenager.
    """
    # If there is no input, or just whitespace
    # we return
    if not what or what.isspace():
        return 'Fine. Be that way!'
    # All questions are answered with Sure.
    # Although if we're shouting the answer isn't as certain
    elif what.endswith('?') and not what.isupper():
        return 'Sure.'
    # If we're shouting at him, he asks us to chill out
    elif what.isupper():
        return 'Whoa, chill out!'
    # The rest of the questions seemed to be junk, and not very
    # easy to categorise. So here's our catch all response.
    else:
        return 'Whatever.'
