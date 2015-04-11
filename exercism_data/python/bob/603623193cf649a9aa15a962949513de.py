#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.strip():
        if what.isupper():
            return "Whoa, chill out!"
        elif what.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
    else: 
        return "Fine. Be that way!"
