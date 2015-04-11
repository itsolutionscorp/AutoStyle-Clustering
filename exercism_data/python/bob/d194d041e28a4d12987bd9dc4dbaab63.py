def hey(what):
    what_strip = what.strip()
    if not what_strip:
        return "Fine. Be that way!"
    elif what_strip.isupper():
        return "Whoa, chill out!"
    elif what_strip[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
