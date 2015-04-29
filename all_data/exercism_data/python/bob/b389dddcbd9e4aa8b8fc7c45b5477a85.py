def hey(n):
    print n
    if len(n) > 1:
        if n.isspace():
            return 'Fine. Be that way!'
        uppercount = sum(1 for c in n if c.isupper())
        lowercount = sum(1 for c in n if c.islower())
        if (uppercount > 0) and (lowercount == 0):
            return 'Whoa, chill out!'
        if n[-1] == "?":
            return 'Sure.'
    else:
        return "Fine. Be that way!"
    return 'Whatever.'
