# Skeleton file for the Python "Bob" exercise.
def hey(what):

    response = "Whatever."

    if not what.strip():
        response = 'Fine. Be that way!'
    elif what.strip().isupper():
        response = 'Whoa, chill out!'
    elif what.strip()[-1] == "?":
        response = 'Sure.'

    return response
