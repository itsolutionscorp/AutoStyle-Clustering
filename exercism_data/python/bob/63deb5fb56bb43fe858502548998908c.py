def hey(message):
    if message.isupper():
        return "Woah, chill out!"
    elif message.endswith('?'):
        return "Sure."
    elif not message.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."
