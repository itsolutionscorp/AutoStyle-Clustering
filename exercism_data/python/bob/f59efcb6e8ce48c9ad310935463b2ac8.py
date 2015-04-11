#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    whatstrp = what.strip()

    if not whatstrp:
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if whatstrp.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
