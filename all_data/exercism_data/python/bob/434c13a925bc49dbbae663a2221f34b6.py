def hey(message):
    """
    :param  message   String with a message

    :returns  String with a message
    """
    # Check if it's an empty string
    if not message.strip():
        return 'Fine. Be that way!'
    # Verify uppercase string and make sure it changes if lowered
    elif message == message.upper() and message != message.lower():
        return 'Whoa, chill out!'
    # Search for questions
    elif message[-1:] == '?':
        return 'Sure.'
    return 'Whatever.'
