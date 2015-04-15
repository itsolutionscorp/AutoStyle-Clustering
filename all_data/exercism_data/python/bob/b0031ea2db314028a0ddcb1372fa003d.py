
# Return a response based on the content of the passed-in message
def hey(message):
    # Shouting corresponds to all-uppercase strings
    if message.isupper():
        return 'Whoa, chill out!'
    # If it's not shouting, then it's a question when it ends with a question mark
    elif message.endswith('?'):
        return 'Sure.'
    # Blank messages or messages consisting purely of whitespace
    elif not message or message.isspace():
        return 'Fine. Be that way!'
    # All other queries
    else:
        return 'Whatever.'
