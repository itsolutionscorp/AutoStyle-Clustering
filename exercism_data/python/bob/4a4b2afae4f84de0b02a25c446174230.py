#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what is not None:

        what = what.strip()

        if what.isupper():
            return 'Whoa, chill out!'
        elif what.endswith('?'):
            return 'Sure.'
        elif what == '':
            return 'Fine. Be that way!'

    return 'Whatever.'