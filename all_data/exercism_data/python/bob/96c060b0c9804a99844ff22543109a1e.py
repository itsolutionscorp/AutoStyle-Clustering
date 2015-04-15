#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    w = what.strip()
    if not w:
        return "Fine. Be that way!"
    elif w.isupper():
        return "Whoa, chill out!"
    elif w[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
