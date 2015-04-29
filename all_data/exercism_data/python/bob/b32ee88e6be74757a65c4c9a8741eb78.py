def hey(what):
    if not what.strip():
        return "Fine. Be that way!"

    # yelling?
    if what.isupper():
        # there's at least one uppercase letter and no lowercase letters
        return "Whoa, chill out!"

    # a question?
    if what.endswith('?'):
        return "Sure."

    # default: attitude
    return "Whatever."
