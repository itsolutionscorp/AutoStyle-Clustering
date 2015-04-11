#
# Skeleton file for the Python "Bob" exercise.
#

import re

def is_capslocked(letter):
    # Letter can be typed with the capslock key on.
    return not letter.islower()

def is_shouting(word):
    # Must be typing with the capslock key on
    # and using at least one letter
    return (all(is_capslocked(char) for char in word) and
           any(char.isalpha() for char in word))

def hey(what):
    what = what.strip()

    if not what:
        return 'Fine. Be that way!'

    if is_shouting(what):
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    else:
        return 'Whatever.'

    return
