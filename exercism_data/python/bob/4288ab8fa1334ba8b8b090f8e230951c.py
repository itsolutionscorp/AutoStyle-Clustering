import string

def is_shouting(message):
    # Not any ordinary letters?
    if not any(ch in string.letters for ch in message):
        return False

    # If it doesn't end with '!' we must have all strings printable to
    # be able to determine whether all characters are uppercase.
    if message[-1] != '!' and not all(ch in string.printable for ch in message):
        return False

    return message.upper() == message

def hey(message):
    message = message.strip()

    if not message:
        return 'Fine. Be that way!'
    elif is_shouting(message):
        return 'Woah, chill out!'
    elif message[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
