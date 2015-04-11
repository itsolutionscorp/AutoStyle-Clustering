def hey(message):

    if not message.strip():
        return 'Fine. Be that way!'

    if message.upper() == message and any(char.isalpha() for char in message):
        return 'Whoa, chill out!'

    if message[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
