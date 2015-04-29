#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what or what is None or what.isspace():
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.replace(' ', '')[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
