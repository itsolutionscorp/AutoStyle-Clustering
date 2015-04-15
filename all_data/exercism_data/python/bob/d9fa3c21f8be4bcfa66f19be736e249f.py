#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what.strip() == '':
        return 'Fine. Be that way!'

    a_to_z = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

    if any((c in a_to_z) for c in what) and what.upper() == what:
        return 'Whoa, chill out!'

    if what.strip().endswith('?'):
        return 'Sure.'

    return 'Whatever.'
