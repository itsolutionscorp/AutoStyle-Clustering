def hey(what):
    # check if instance is the class str or unicode
    if isinstance(what, basestring):
        if not what.strip():
            return "Fine. Be that way!"
        if not what.islower() and what.isupper():
            return "Whoa, chill out!"
        if what.strip().endswith("?"):
            return "Sure."
    return "Whatever."
