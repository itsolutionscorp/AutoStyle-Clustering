#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # get rid of all whitespace characters in the beginning and end of the string
    what = what.strip()

    # check for shouting
    if what.isupper():
        return 'Whoa, chill out!'
    # check for question
    elif what.endswith('?'):
        return 'Sure.'

    return 'Fine. Be that way!' if not what else 'Whatever.'
