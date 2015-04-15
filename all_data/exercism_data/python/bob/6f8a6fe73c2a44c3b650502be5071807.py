#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # contains nothing but spaces
    if 0 == len(''.join(what.split())):
        return 'Fine. Be that way!'

    # all characters are UPPER -> shouting
    elif what.isupper():
        return 'Whoa, chill out!'

    # ends with question mark
    elif '?' == what[-1]:
        return 'Sure.'

    # else
    return 'Whatever.'
