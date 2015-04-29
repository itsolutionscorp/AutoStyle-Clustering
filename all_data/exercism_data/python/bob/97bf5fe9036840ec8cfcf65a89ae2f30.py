def hey(inp):
    if inp.strip() == "":
        return "Fine. Be that way!"
    if inp == inp.upper() and inp != inp.lower():
        return "Whoa, chill out!"
    if len(inp) > 1 and inp[-1] == "?":
        return "Sure."
    return "Whatever."
