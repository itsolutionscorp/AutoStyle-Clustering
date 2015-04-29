def is_shouting(message):
    return message.isupper()


def is_question(message):
    return message.endswith("?")


def is_silence(message):
    return message == "" or message.isspace()


def hey(message):
    if is_shouting(message):
        return "Whoa, chill out!"
    elif is_question(message):
        return "Sure."
    elif is_silence(message):
        return "Fine. Be that way!"
    else:
        return "Whatever."
