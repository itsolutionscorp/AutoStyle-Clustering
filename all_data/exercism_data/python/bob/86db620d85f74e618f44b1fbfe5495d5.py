#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what is None or what.strip() == '':
        return 'Fine. Be that way!'
    elif what.strip().isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
