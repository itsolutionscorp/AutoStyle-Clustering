def hey(what):
    # silence?
    if not what.strip():
        return "Fine. Be that way!"

    # yelling?
    if what.isupper():
        # there's at least one uppercase letter and no lowercase letters
        return "Whoa, chill out!"

    # question?
    if what.endswith('?'):
        return "Sure."

    # default: attitude
    return "Whatever."
