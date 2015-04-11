#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    trimmed = what.strip()
    if len(trimmed) == 0:
        return 'Fine. Be that way!'
    elif trimmed.isupper():
        return 'Whoa, chill out!'
    elif trimmed.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
    return
