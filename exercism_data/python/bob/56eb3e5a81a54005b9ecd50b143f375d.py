#
# Skeleton file for the Python "Bob" exercise.
#

import string


def hey(what):
    no_meaning = True
    is_question = False

    is_uppercase = what.isupper()

    for c in what:
        if c in string.letters:
            no_meaning = False
        if c in string.digits:
            no_meaning = False

        if c not in string.whitespace:
            is_question = False
        if c == '?':
            is_question = True

    if no_meaning:
        return 'Fine. Be that way!'
    if is_uppercase:
        return 'Whoa, chill out!'
    if is_question:
        return 'Sure.'
    else:
        return 'Whatever.'
