def hey(strthing):
    if not strthing.strip(): # empty string, this can be anywhere
        return "Fine. Be that way!"
    elif strthing.isupper(): # all upper case this must be before the question mark check
        return "Whoa, chill out!"
    elif strthing[-1:] == "?": # ends in a question mark
        return "Sure."
    else: # everything else
        return "Whatever."
