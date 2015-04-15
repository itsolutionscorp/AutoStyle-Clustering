#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    elif len(what.strip()) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
