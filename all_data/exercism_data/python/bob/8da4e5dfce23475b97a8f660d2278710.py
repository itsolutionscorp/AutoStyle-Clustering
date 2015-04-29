"""The "Bob" exercise"""
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    """Bob's response"""

    what = what.strip()

    is_yelling = what.isupper()
    are_words = any(c.isalpha() for c in what)

    if what == "":
        return 'Fine. Be that way!'

    if is_yelling and are_words:
        return 'Whoa, chill out!'

    if what[-1] == "?":
        return 'Sure.'

    return 'Whatever.'
