def hey(input):
    if input is None or input.strip() == '':
        return "Fine. Be that way!"
    if input.isupper(): 
        return "Whoa, chill out!"
    if input[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
