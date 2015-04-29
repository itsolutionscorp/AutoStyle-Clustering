def hey(message):
    if (not message.strip()):
        return 'Fine. Be that way!'

    if (message.isupper()):
        return 'Whoa, chill out!'

    if (message[len(message) - 1] == '?'):
        return 'Sure.'

    return 'Whatever.'
