# CODING=utf-8
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    stripped = what.strip()
    if not stripped:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif stripped.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
