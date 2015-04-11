def hey(message):
    if (message.isupper()):
        return "Whoa, chill out!"
    if (not message or message.isspace()):
        return "Fine. Be that way!"
    if (message.endswith("?")):
        return "Sure."
        
    return "Whatever."
