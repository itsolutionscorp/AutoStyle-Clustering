#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    trimmedwhat = what.strip()

    if trimmedwhat == '':
        return 'Fine. Be that way!'
    if trimmedwhat.isupper():
        return 'Whoa, chill out!'
    if trimmedwhat.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
