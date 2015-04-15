def hey(inp):
    inp = inp.strip()                         # Strip out any whitespace
    if(inp == ""):                            # If the string was empty/only whitespace
        return "Fine. Be that way!"
    elif(inp.upper() == inp
         and any(c.isalpha() for c in inp)):  # If the string is in all-caps(yelling) AND there are actual letters
        return "Whoa, chill out!"
    elif(inp[-1] == "?"):                     # If it's a question
        return "Sure."
    else:                                     # Anything else
        return "Whatever."
