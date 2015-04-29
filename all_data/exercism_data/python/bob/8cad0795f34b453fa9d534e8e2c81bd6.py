#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.rstrip().endswith('?') and not what.rstrip().isupper():
        return 'Sure.'
    elif what.rstrip().endswith('!') or what.rstrip().isupper():
        return 'Whoa, chill out!'
    elif len(what.rstrip()) == 0:
        return 'Fine. Be that way!'
    return 'Whatever.'
