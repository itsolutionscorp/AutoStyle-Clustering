def silence(message):
    return not message

def shouting(message):
    return message.isupper()

def question(message):
    return message.endswith("?")

def hey(message=""):
    message = message.strip()
    if silence(message):
        return "Fine. Be that way!"
    if shouting(message):
        return "Whoa, chill out!"
    if question(message):
        return "Sure."
    return "Whatever."
