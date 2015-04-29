def _is_a_yell(message):
    return message.isupper()

def _is_a_question(message):
    return message.endswith('?') and not _is_a_yell(message)

def _is_nothing(message):
    if message.strip() == '':
        return True

def hey(message):
    if _is_a_question(message):
        return 'Sure.'
    if _is_a_yell(message):
        return 'Whoa, chill out!'
    if _is_nothing(message):
        return 'Fine. Be that way!'
    return 'Whatever.'
