def hey(inp):
    inp = inp.strip()
    if(inp == ""):
        return "Fine. Be that way!"
    elif(inp.isupper() and any(c.isalpha() for c in inp)):
        # If input is in all-caps and is not just symbols/numbers
        return "Whoa, chill out!"
    elif(inp.endswith("?")):
        return "Sure."
    else:
        return "Whatever."
