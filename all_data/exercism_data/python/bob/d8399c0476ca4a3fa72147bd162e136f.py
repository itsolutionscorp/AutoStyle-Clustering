#
# Skeleton file for the Python "Bob" exercise.
#

def is_empty(what):
    return len(what.strip()) == 0


def is_a_question(what):
    return what.endswith('?')


def is_shouting(what):
    return what.isupper()


def hey(what):

    if is_empty(what):
        return 'Fine. Be that way!'

    if is_shouting(what):
        return 'Whoa, chill out!'

    if is_a_question(what):
        return 'Sure.'

    return 'Whatever.'
