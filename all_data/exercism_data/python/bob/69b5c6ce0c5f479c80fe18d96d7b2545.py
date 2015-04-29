def hey(something):
    if len(something) == 0:
        return "Fine. Be that way!"
    
    
    nothing = True
    
    i = 0
    while i < len(something):
        if something[i] not in {" ", "\n", "\t"}:
            nothing = False
            break
        i += 1
    if nothing == True:
        return "Fine. Be that way!"

    if something == something.upper() and something != something.lower():
        return "Woah, chill out!"

    if something[-1] == "?":
        return "Sure."

    else:
        return "Whatever."
