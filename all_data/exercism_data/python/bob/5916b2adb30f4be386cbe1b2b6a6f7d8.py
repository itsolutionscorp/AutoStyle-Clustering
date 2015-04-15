import string

def hey(what):
    if what == "" or all(c in string.whitespace for c in what):
        return "Fine. Be that way!"
    if any(c in string.ascii_letters for c in what) and what == what.upper():
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."
