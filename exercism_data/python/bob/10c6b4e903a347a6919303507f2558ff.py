#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if any(c.isupper() for c in what) and not any(c.islower() for c in what):
        return 'Whoa, chill out!'
    if what.strip().endswith('?'):
        return 'Sure.'
    elif all(c.isspace() for c in what):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
