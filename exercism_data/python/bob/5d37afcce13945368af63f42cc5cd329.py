import re

def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif re.match(r"^\s*$", what):
        return "Fine. Be that way!"
    return "Whatever."
