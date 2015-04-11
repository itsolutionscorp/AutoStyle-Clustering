def hey(what):
    x=True
    for char in what:
        if x==True:
            if char == " " or char == "\t" or char == "\n":
                x=True
            else:
                x=False
        else:
            x=False
    if x==True:
        return "Fine. Be that way!"
    elif what == what.upper() and what != what.lower():
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
