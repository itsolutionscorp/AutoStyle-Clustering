#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif not what.strip():
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'