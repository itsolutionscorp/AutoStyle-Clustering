import re

def hey(what):
    what = what.strip()

    if re.search('[a-zA-Z]', what) and what == what.upper():
        return "Whoa, chill out!"
    elif what.endswith('?'):
        return "Sure."
    elif not what:
        return "Fine. Be that way!"

    return "Whatever."
