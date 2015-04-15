#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """Respond like a surly teenager."""

    what = what.strip()

    if what == '':
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
