def is_silence(message):
    return message == "" or message.isspace()

def is_shouting(message):
    return any((s.isalpha() for s in message)) and\
           message.upper() == message

def is_question(message):
    return message == "" or message[-1] == '?'

def hey(message):
    if is_silence(message):
        return "Fine. Be that way!"
    if is_shouting(message):
        return "Whoa, chill out!"
    if is_question(message):
        return "Sure."

    return "Whatever."
