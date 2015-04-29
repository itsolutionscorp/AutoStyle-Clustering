#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isspace() or what == '':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
