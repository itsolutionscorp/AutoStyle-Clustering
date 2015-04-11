#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
