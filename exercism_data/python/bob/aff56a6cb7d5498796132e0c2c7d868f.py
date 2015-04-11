#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what or not what.strip():
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
