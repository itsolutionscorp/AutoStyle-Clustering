#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.isupper():
        response = "Whoa, chill out!"
    elif what.endswith('?'):
        response = "Sure."
    elif not what:
        response = "Fine. Be that way!"
    else:
        response = "Whatever."
    return response
