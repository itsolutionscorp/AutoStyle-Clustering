def hey(n):
    if not n or n.isspace():
        return 'Fine. Be that way!'
    uppercount = sum(1 for c in n if c.isupper())
    lowercount = sum(1 for c in n if c.islower())
    if (uppercount > 0) and (lowercount == 0):
        return 'Whoa, chill out!'
    if n[-1] == "?":
        return 'Sure.'

    return 'Whatever.'
