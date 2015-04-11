def hey(sent):
    if sent.strip() == "": 
        return "Fine. Be that way!"
    
    elif sent.isupper():
        return "Woah, chill out!"

    elif sent[-1] == '?':
        return "Sure."
    
    else: 
        return "Whatever."
