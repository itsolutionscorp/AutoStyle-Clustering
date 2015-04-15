#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    test = what.strip()
    result = ''

    if test == '':
        result = "Fine. Be that way!"

    elif test.isupper():
        result = "Whoa, chill out!"

    elif test.endswith('?') == True:
        result = "Sure."

    else:
        result = "Whatever."

    return result
