def hey(what):

    what = what.strip()  # makes it robust to spaces at the ends
    
    # does it not have at least one alphanumeric?
    if not any([char.isalnum() for char in what]):
        return "Fine. Be that way!"

    elif what.isupper():
        return "Whoa, chill out!"

    elif what[-1] == "?":
        return "Sure."

    else:
        return "Whatever."
