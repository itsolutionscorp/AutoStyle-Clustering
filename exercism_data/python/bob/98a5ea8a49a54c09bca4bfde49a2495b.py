import re

def hey(what):
    if re.search('[a-zA-Z]', what) and what == what.upper():
        return "Whoa, chill out!"
    elif what.endswith('?'):
        return "Sure."
    elif not re.sub('\s', '', what):
        return "Fine. Be that way!"

    return "Whatever."
