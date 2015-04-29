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

    answer = [u'    \t', u'',]

    if not what.strip():
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if what.strip()[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
