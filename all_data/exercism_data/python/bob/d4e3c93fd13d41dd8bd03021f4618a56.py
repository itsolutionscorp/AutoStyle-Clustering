#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what[:].strip() == '':
        return 'Fine. Be that way!'
    elif what.strip().endswith('?') and not what.isupper():
        return 'Sure.'
    elif what.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
