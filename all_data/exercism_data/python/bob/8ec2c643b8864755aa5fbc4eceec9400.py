#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    content = what.lstrip()
    if content == '':
        return "Fine. Be that way!"
    elif content.isupper():
        return "Whoa, chill out!"
    elif content.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
