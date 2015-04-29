"""
bob - Implement the correct solution to the 'bob' problem.
"""


def _is_shouting(what):
    """
    Return True if `what` contains shouting.

    `what` is considered to be shouting if it contains capital letters but no
    lowercase letters.
    """
    contains_upper = False
    for c in what:
        if c.isupper():
            contains_upper = True
        elif c.islower():
            return False
    return contains_upper


def hey(what):
    """
    Generate and return Bob's correct response to `what`.
    """
    what = what.strip()
    if not what:
        return u'Fine. Be that way!'
    if _is_shouting(what):
        return u'Whoa, chill out!'
    if what.endswith('?'):
        return u'Sure.'

    return u'Whatever.'
