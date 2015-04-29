#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    response = "Whatever."
    what = what.strip()
    if not what:
        response = "Fine. Be that way!"
    elif what.isupper():
        response = "Whoa, chill out!"
    elif what.endswith("?"):
        response = "Sure."
    return response
