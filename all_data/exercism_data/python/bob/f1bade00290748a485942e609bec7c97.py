def hey(saying=" "):
    if not saying or saying.isspace():
        return unicode('Fine. Be that way!')
    elif saying.isupper():
        return unicode("Whoa, chill out!")
    elif saying[-1:] == "?":
        return unicode("Sure.")
    else:
        return unicode("Whatever.")
