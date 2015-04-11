#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.strip().isupper() or (what.strip().isupper() and what.strip().endswith('?')):
        return 'Whoa, chill out!'
    if what.strip().endswith('?'):
        return 'Sure.'
    elif not what.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
