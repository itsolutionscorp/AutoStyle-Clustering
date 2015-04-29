#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    # Checks if 'what' is all upper case.
    if(what.isupper()):
        return "Whoa, chill out!"

    # Checks if 'what' ends in a question mark.
    elif(what[len(what) - 1:] == '?'):
        return "Sure."

    # Strips 'what' of any leading or trailing whitespace, then checks if any
    # content is left.
    elif(what.strip() == ''):
        return "Fine. Be that way!"

    else:
        return "Whatever."

    return
