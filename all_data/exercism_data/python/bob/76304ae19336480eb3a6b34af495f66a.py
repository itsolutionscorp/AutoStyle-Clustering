def isQuestion(message):
    if (message):
        return message[-1] == '?'
    else:
        return False


def hey(message):
    if not message.isupper() and isQuestion(message):
        return "Sure."
    elif message.isupper():
        return "Woah, chill out!"
    elif not message.strip():
        return "Fine. Be that way!"
    else:
        return "Whatever."
