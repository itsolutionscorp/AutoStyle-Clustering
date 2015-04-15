# -*-coding: utf-8-*-

def hey(str=""):

    if(str.strip() == ""):
        return "Fine. Be that way!"
    elif(str.isupper()):
        return "Whoa, chill out!"
    elif(str.endswith("?")):
        return "Sure."
    else:
        return "Whatever."
