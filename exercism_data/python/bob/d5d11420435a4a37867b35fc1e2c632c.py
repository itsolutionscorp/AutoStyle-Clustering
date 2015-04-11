def hey(message):
    message = "".join(message.split())

    if not message:
        return 'Fine. Be that way!'

    if any(d.isalpha() for d in message) and all(c.isupper() for c in message if c.isalpha()):
        return 'Whoa, chill out!'

    if message[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
