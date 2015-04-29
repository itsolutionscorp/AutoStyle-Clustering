#
# Skeleton file for the Python "Bob" exercise.
#
import string


def has_letters(s):
    return set(string.ascii_letters) != set(string.ascii_letters) - set(s)


def is_all_uppercase(s):
    return s == s.upper()


def has_only_whitespace(s):
    return not ''.join(s.split())


def hey(what):

    if has_letters(what) and is_all_uppercase(what):
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    if has_only_whitespace(what):
        return 'Fine. Be that way!'

    return "Whatever."
