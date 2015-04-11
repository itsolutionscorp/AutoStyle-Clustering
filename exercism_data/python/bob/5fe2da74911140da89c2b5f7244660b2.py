YELL_REPLY = 'Whoa, chill out!'
QUESTION_REPLY = 'Sure.'
ANYTHING_ELSE_REPLY = 'Whatever.'
EMPTY_QUESTION_REPLY = 'Fine. Be that way!'


def hey(message):
    """
    What should Bob return with this string HOW ARE YOU?
    """
    if not message.strip():
        return EMPTY_QUESTION_REPLY
    if message.isupper():
        return YELL_REPLY
    if message.endswith('?'):
        return QUESTION_REPLY
    return ANYTHING_ELSE_REPLY
