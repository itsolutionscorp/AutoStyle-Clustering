def hey(message):

    if message.isspace() or message == "":
        return "Fine. Be that way!"
    elif message.isupper() :
        return "Whoa, chill out!"
    elif message[-1:] == "?":
        return "Sure."
    else:
        return "Whatever."
