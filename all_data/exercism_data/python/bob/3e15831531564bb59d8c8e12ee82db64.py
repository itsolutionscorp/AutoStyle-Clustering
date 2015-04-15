def hey(what):
    what = what.strip()
    if what.isupper() == True:
        return "Whoa, chill out!"
    elif len(what) == 0:
        return "Fine. Be that way!"
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
