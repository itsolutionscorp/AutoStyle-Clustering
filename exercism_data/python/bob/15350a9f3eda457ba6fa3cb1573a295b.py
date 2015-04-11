#!/usr/bin/python

def hey(yo):
    #check for blanks
    if yo.isspace() or yo == "":
        return "Fine. Be that way!"
    #check for all caps
    elif yo.isupper():
        return "Whoa, chill out!"
    #check for a ? at the end
    elif yo[len(yo)-1] == "?":
        return "Sure."
    #The rest
    else:
        return "Whatever."
