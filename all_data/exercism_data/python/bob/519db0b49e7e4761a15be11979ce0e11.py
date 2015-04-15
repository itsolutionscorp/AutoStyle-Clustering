def hey(message):
    if message.strip() == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Woah, chill out!'
    if message[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
