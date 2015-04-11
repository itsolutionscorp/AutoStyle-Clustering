def hey(message):
    '''
    returns 'Sure.' if message ends in '?'
    returns 'Whoa, chill out!' if message is all uperrcase
    returns 'Fine. Be that way!' if message is just whitespace
    returns 'Whatever.' otherwise.
    '''

    if message.isspace() or message == "":
        return "Fine. Be that way!"
    elif message.isupper():
        return "Whoa, chill out!"
    elif message[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
