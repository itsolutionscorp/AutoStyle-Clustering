def hey(message):
    # trim the message
    message = message.strip()

    ## empty string
    if message == '':
        return 'Fine. Be that way!'

    ## shouting
    if message.isupper():
        return 'Woah, chill out!'

    ## question
    if message.endswith('?'):
        return 'Sure.'

    ## other
    return 'Whatever.'
