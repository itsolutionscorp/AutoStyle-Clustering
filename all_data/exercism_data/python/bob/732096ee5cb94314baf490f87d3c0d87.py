def hey(what):
    # remove blank space at begin and end
    what = what.strip()

    if len(what) == 0:                  # say nothing?
        return "Fine. Be that way!"
    elif what.isupper() is True:        # SAY OUT LOUD?
        return 'Whoa, chill out!'
    elif what[-1] == "?":               # end by question mark?
        return 'Sure.'
    else return "Whatever."             # anything else
