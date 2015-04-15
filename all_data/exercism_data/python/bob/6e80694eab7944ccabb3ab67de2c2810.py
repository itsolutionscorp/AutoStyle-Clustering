#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Strip the whitespace
    what = what.strip()
    # Check for all caps
    if what.isupper():
        return 'Whoa, chill out!'
    # Check for ending with ?
    if what.endswith('?'):
        return 'Sure.'
    # Check for actual words
    if not what:
        return 'Fine. Be that way!'
    # Default reply
    return 'Whatever.'
