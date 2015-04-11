#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    response = "Whatever."
    what = what.strip()

    if len(what) == 0:
        response = 'Fine. Be that way!'
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what.find('?') == len(what) - 1:
        response = "Sure."

    return response
