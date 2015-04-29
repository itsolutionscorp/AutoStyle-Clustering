#
# Skeleton file for the Python "Bob" exercise.
#

from string import digits, punctuation, whitespace

NOT_LETTERS = set(digits + punctuation + whitespace)


def hey(what):

    just_letters = filter(lambda x: x not in NOT_LETTERS, what)

    if not what.strip():
        return 'Fine. Be that way!'
    elif just_letters and just_letters.upper() == just_letters:
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
