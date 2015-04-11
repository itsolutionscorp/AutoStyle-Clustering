#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    # Checks if 'what' is all upper case.
    if what.isupper():
        return "Whoa, chill out!"

    # Strips 'what' of any leading or trailing whitespace, then checks if any
    # content is left.
    elif what.strip() == '':
        return "Fine. Be that way!"

    # Checks if the last character of 'what' is a question mark.
    # At least one character is guaranteed due to the previous check,
    # preventing "index out of range" errors.
    elif what[-1] == '?':
        return "Sure."

    else:
        return "Whatever."

    return
