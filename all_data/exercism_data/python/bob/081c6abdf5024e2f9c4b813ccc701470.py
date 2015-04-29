def hey(message):
    message = "".join(message.split())

    if not message:
        return 'Fine. Be that way!'

    if message.isupper():
        return 'Whoa, chill out!'

    if message[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
