def hey(message):
    if len(message) == 0 or message.isspace():
        return "Fine. Be that way!"
    
    elif message.isupper():
        return "Whoa, chill out!"

    elif message.endswith('?'):
        return "Sure."

    else:
        return "Whatever."
