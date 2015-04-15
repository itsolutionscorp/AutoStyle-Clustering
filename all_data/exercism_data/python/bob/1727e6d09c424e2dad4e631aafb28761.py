def hey(message):
    message = message.strip()
    if not message:
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Woah, chill out!'
    if message[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
