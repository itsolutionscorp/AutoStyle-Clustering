
def hey(message):
    text = message.strip()
    is_upper = text.upper() == text
    is_alpha = not (text.upper() == text.lower())
    if not text:
        result = 'Fine. Be that way!'
    elif is_upper and is_alpha:
        result = 'Whoa, chill out!'
    elif text.endswith('?'):
        result = 'Sure.'
    else:
        result = 'Whatever.'
    return result
