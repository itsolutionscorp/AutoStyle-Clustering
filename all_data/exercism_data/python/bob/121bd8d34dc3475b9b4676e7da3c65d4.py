# -*- coding: utf-8 -*-
u"""Bob, the lackadaisical teenager.

    Address bob with hey().
    """


def hey(input_string):
    u"""Returns a response string when given some string input."""

    # Is there actually text here?
    if input_string.strip() == '':
        return u"Fine. Be that way!"

    # Is the input considered uppercase (ie. yelling)?
    if input_string.isupper():
        return u"Whoa, chill out!"

    # Is the input a question?
    if input_string.endswith('?'):
        return u"Sure."

    return u"Whatever."
