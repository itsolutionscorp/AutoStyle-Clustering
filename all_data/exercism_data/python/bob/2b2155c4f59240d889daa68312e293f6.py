#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # contains nothing or just spaces
    #if 0 == len(''.join(what.split())):
    if what.isspace() or 0 == len(what):
        return 'Fine. Be that way!'

    # all characters are UPPER -> shouting
    elif what.isupper():
        return 'Whoa, chill out!'

    # ends with question mark
    #elif '?' == what[-1]:
    elif what.endswith('?'):
        return 'Sure.'

    # else
    return 'Whatever.'
