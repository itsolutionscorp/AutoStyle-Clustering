def hey(raw_message):
    """Returns Bob's response to a message string"""
    message = raw_message.rstrip()

    if _is_silence(message):
        return 'Fine. Be that way!'

    if _is_yell(message):
        return 'Whoa, chill out!'

    if _is_question(message):
        return 'Sure.'

    return 'Whatever.'

def _is_silence(message):
    return '' == message

def _is_yell(message):
    return message.isupper()

def _is_question(message):
    return message.endswith('?')
