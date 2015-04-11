def hey(message):
    message = message.strip()
    if not message:
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Woah, chill out!'
    elif message[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
