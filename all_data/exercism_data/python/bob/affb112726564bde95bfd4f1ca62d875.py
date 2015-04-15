#!/usr/bin/python3

"""Solution for exercism.io's first Python exercice
"""

import re


def _isSilence(message):
    """Returns true if message is empty or filled with whitespace characters"""
    return re.match(r'^\s*$', message)


def _isAllCaps(message):
    """Returns true if message is upper-case"""
    return re.match(r'^[^a-zà-ÿ]*[A-Z][^a-zà-ÿ]*$', message)


def _isAQuestion(message):
    """Returns true if message ends with a question mark"""
    if len(message) == 0:
        return False
    return (message[-1] == '?')


class Bob:
    """A lackadaisical teenager

    When interacted with through the ``hey`` method, anwsers with a reply
    picked among a restricted set by following simple rules.
    """
    def hey(self, message):
        """Returns a string depending on the contents of message"""
        if not issubclass(type(message), str):
            raise TypeError("message must be a string")
        if _isSilence(message):
            return 'Fine. Be that way!'
        if _isAllCaps(message):
            return 'Woah, chill out!'
        if _isAQuestion(message):
            return 'Sure.'
        return 'Whatever.'
