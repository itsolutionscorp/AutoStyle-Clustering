def hey(what):
    response = "Whatever."
    what = what.strip()

    if len(what) == 0:
        response = 'Fine. Be that way!'
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what[-1:] == '?':
        response = "Sure."

    return response
