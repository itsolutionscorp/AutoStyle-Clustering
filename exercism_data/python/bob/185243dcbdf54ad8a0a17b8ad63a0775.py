def hey(message):
    '''
    returns 'Sure.' if message ends in "?"
    returns 'Whoa, chill out!' if message is all uperrcase"
    returns 'Fine. Be that way!' if message is whitespace"
    '''

    if message.isspace() or message == "":
        return "Fine. Be that way!"

    if message.isupper():
        return "Whoa, chill out!"

    if message[-1] == "?":
        return "Sure."

    return "Whatever."
