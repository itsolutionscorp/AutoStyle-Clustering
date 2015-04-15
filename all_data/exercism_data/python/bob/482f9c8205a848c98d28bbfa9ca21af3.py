#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif what == '' or len(what) == 2:
        return 'Fine. Be that way!'
    return 'Whatever.'
