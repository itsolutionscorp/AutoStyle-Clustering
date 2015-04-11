def hey(what):
    what = what.strip()
    if what.isupper() == True:
        return "Whoa, chill out!"
    if len(what) == 0:
        return "Fine. Be that way!"
    if what.endswith('?'):
        return "Sure."
    return "Whatever."
