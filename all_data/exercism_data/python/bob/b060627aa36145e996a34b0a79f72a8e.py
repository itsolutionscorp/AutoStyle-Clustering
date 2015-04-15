import re as regex


def hey(message):
    if(message.strip() == ""):
        return "Fine. Be that way!"
    elif(message.upper() == message and regex.search("[A-Z]+", message) != None):
        return "Whoa, chill out!"
    elif(regex.search("\\?$", message) != None):
        return "Sure."
    else:
        return "Whatever."
