# coding=utf-8
#
# Skeleton file for the Python "Bob" exercise.
#
#
"""
Bob's answer

"""
def hey(what):
    """
    Respond to a sentence
    """

    what = what.strip()

    if not what:
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
