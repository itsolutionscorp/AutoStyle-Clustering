#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
    if what.strip() == '':
        return "Fine. Be that way!"
    elif what.upper() == what and len(set(string.ascii_letters).intersection(set(what))) > 0:
        return "Whoa, chill out!"
    elif what.strip()[-1] == '?':
        return "Sure."
    return "Whatever."
