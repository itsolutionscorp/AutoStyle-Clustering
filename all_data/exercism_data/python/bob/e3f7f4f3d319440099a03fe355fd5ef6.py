#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what is None or what.strip() == '':
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.strip().endswith('?'):
        return 'Sure.'

    return "Whatever."
