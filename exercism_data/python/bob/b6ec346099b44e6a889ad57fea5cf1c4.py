#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == what.upper() and not any(c.islower() for c in what) and any(c.isalpha() for c in what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif not what.strip():
        return 'Fine. Be that way!'
    return 'Whatever.'
