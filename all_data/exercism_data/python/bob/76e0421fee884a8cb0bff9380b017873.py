#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # preprocess
    what = what.strip()

    # logic
    if not what:
        return 'Fine. Be that way!'

    elif what.isupper():
        return 'Whoa, chill out!'

    elif what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
